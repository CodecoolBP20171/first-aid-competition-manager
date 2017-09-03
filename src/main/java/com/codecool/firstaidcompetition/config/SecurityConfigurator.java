package com.codecool.firstaidcompetition.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by keli on 2017.09.01..
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfigurator extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
        auth.
            inMemoryAuthentication().
                withUser("keli").password("asd").
                roles("ADMIN").
            and().
                withUser("joe").password("pass").roles("USER");
    }

    @Override   // configure our security policy
    protected void configure(HttpSecurity http) throws Exception {
        http.
            authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
            .and()
                .logout()
                .logoutSuccessUrl("/index")
                .permitAll();
    }


}
