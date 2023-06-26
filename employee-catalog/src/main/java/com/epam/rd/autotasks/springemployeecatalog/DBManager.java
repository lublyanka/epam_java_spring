package com.epam.rd.autotasks.springemployeecatalog;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j2
public final class DBManager {

    private static DBManager instance;
    public final static PropertiesLoader properties = PropertiesLoader.getInstance();


    private DBManager() {
    }

    public static DBManager getInstance() {
        {
            if (instance != null)
                return instance;

            synchronized (DBManager.class) {
                if (instance == null) {
                    instance = new DBManager();
                }
                return instance;
            }
        }
    }

    public <T> T execute(CheckedFunction<Connection, T> action) {
        try (Connection con = DriverManager.getConnection(properties.getProperties().getProperty("spring.datasource.url"),
                properties.getProperties().getProperty("spring.datasource.username"),
                properties.getProperties().getProperty("spring.datasource.password"))) {
            try {
                con.setAutoCommit(false);
                T result = action.apply(con);
                con.commit();
                //con.setAutoCommit(true);
                return result;
            } catch (SQLException e) {
                log.error(e);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }
}