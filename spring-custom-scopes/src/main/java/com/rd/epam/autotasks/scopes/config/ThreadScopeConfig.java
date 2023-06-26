package com.rd.epam.autotasks.scopes.config;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ThreadScopeConfig {
    @Bean
    @Scope("thread")
    public Object myBean() {
        return new Object();
    }

    @Bean
    public static ThreadScope threadScope() {
        return new ThreadScope();
    }

    @Bean
    public static CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.addScope("thread", threadScope());
        return configurer;
    }

}
