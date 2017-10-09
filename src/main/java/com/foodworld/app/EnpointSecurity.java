/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani
 ******************************************************************************/

package com.foodworld.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class EnpointSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;

    @Autowired
    CorsFilter corsFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("ugfg8945gcg").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user").password("gjhvjhv+5+99").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.addFilterAfter(corsFilter, BasicAuthenticationFilter.class);
        http.authorizeRequests().antMatchers("/foodworld/*/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/foodworld/*/admin/**").hasRole("ADMIN").anyRequest().authenticated().and().httpBasic()
                .authenticationEntryPoint(customBasicAuthenticationEntryPoint);

    }
  
}
