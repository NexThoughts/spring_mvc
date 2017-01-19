package com.nexthoughts.spring.mvc.demo.config;

import com.nexthoughts.spring.mvc.demo.services.security.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebMvcSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AppUserDetailsService appUserDetailsService;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //roles() can be replaced with authorities("ROLE_USER")

/*      // In Memory Authentication
        auth.inMemoryAuthentication()
                .withUser("user").password("password").authorities("ROLE_USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
*/

        auth.userDetailsService(appUserDetailsService).passwordEncoder(getPasswordEncoder());

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login").permitAll().successForwardUrl("/loginHandler")
                .and().authorizeRequests().antMatchers("/signup").permitAll()
                .and().authorizeRequests().antMatchers("/resources/**").permitAll()
                .and().logout().permitAll()
                .and().authorizeRequests().anyRequest().authenticated()
                .and().csrf().disable()

                ;
    }


    @Bean(name = "passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
