package com.marin.urlshortener.config;

import com.marin.urlshortener.service.impl.AccountDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccountDetailsServiceImpl accountDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .userDetailsService(accountDetailsService)
                .cors().and()
                .authorizeRequests()
                .antMatchers("/register", "/statistic/**").authenticated()
                .anyRequest().permitAll()
                .and().httpBasic();

        http.headers().frameOptions().disable();
    }
}
