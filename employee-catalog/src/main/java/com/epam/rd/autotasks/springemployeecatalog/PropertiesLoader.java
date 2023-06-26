package com.epam.rd.autotasks.springemployeecatalog;

import lombok.extern.log4j.Log4j2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class PropertiesLoader {

    private static PropertiesLoader instance;
    private final static Properties properties = new Properties();

    private PropertiesLoader() {
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
            String url = properties.getProperty("spring.datasource.url");
            if (url.isBlank())
                throw new IllegalArgumentException();
            log.info(url);
        } catch (FileNotFoundException fileNotFoundException) {
            log.error("Property file wasn't found");
            throw new IllegalArgumentException("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized static PropertiesLoader getInstance() {
        if (instance != null)
            return instance;

        synchronized (PropertiesLoader.class) {
            if (instance == null) {
                instance = new PropertiesLoader();
            }
            return instance;
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
