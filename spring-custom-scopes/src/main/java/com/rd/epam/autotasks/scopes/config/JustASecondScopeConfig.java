package com.rd.epam.autotasks.scopes.config;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class JustASecondScopeConfig {

    @Bean
    @Scope("justASecond")
    public Object myBean() {
        return new Object();
    }

    @Bean
    public static JustASecondScope justASecondScope() {
        return new JustASecondScope();
    }

    @Bean
    public static CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.addScope("justASecond", justASecondScope());
        return configurer;
    }

}