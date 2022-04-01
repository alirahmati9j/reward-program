package com.ali.rewardprogram.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class LocalWebSecurityConfigurerAdapter
        extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable();

        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll();
    }
}
