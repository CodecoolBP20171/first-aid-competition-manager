package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.model.Role;
import com.codecool.firstaidcompetition.model.User;
import com.codecool.firstaidcompetition.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by keli on 2017.09.07..
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println(name + "  " + password);
        User user = authenticateUser(name, password);
        if (user != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            Set<Role> roles = user.getRole();
            for (Role role : roles){
                grantedAuthorities.add( new SimpleGrantedAuthority(role.getRole()));
            }
            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuthorities);
            return auth;
        } else {
            return null;
        }
//        if (name.equals("admin") && password.equals("system")) {
//            List<GrantedAuthority> grantedAuths = new ArrayList<>();
//            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
//            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
//            return auth;
//        } else {
//            return null;
//        }
    }

    public User authenticateUser(String username, String password){
        User byUsername = userRepository.findByUserName(username);
        User byPassword = authenticateByPassword(password);
        if (byUsername != null && byUsername.getId() == byPassword.getId()){
            return byUsername;
        } else {
            return null;
        }
    }

    private User authenticateByPassword(String password) {
        Iterator<User> users = userRepository.findAll().iterator();
        while (users.hasNext()){
            User currentUser = users.next();
            if (bCryptPasswordEncoder.matches(password, currentUser.getPassword())){
                return currentUser;
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}