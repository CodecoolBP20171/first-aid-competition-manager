package com.codecool.firstaidcompetition.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by keli on 2017.09.01..
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfigurator extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userService;

    @Bean   // Added encoder as bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService);
    }

    @Override   // configure our security policy
    protected void configure(HttpSecurity http) throws Exception {
        // permit all, except route started with '/admin'
        http.
                authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .permitAll()
            .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }
}
