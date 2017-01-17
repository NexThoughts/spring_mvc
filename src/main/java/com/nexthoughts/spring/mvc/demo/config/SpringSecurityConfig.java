package com.nexthoughts.spring.mvc.demo.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@EnableWebMvcSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //roles() can be replaced with authorities("ROLE_USER")

        auth.inMemoryAuthentication()
                .withUser("user").password("password").authorities("ROLE_USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");

    }
}
