package com.codecool.firstaidcompetition.config;

import com.codecool.firstaidcompetition.service.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by keli on 2017.09.01..
 */
@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfigurator extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private CustomAuthenticationProvider customAuthenticationProvider;

    @Bean   // Add encoder as bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Comment in temporary
//    @Autowired
//    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(customAuthenticationProvider);
//    }

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN");
    }

    @Override   // configure our security policy
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/station/**").hasRole("ADMIN")
            .anyRequest().permitAll()
        .and()
            .httpBasic()
        .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
