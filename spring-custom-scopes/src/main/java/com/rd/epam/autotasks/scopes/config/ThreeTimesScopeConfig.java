package com.rd.epam.autotasks.scopes.config;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ThreeTimesScopeConfig {

    @Bean
    @Scope("threeTimes")
    public Object myBean() {
        return new Object();
    }

    @Bean
    public static ThreeTimesScope threeTimesScope() {
        return new ThreeTimesScope();
    }

    @Bean
    public static CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.addScope("threeTimes", threeTimesScope());
        return configurer;
    }


}
