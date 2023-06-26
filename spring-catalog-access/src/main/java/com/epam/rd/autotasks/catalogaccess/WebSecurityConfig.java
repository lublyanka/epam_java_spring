package com.epam.rd.autotasks.catalogaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String MANAGER = "MANAGER";
    private static final String EMPLOYEE = "EMPLOYEE";
    private static final String CUSTOMER = "CUSTOMER";

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("manager").password("password").roles(MANAGER)
                .and()
                .withUser("employee").password("password").roles(EMPLOYEE)
                .and()
                .withUser("customer").password("password").roles(CUSTOMER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // EmployeesController: /employees
                .antMatchers(HttpMethod.POST, "/employees").hasRole(MANAGER)
                .antMatchers(HttpMethod.GET,"/employees","/employees/*").hasAnyRole(MANAGER, EMPLOYEE)
                // SalaryController: /salaries
                .antMatchers("/salaries").hasRole(MANAGER)
                .antMatchers("/salaries/*").hasAnyRole(MANAGER, EMPLOYEE)
                // CatalogController: /catalog
                .antMatchers("/catalog").hasAnyRole(MANAGER, EMPLOYEE, CUSTOMER)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .csrf().disable();
    }
}