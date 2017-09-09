package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.model.Role;
import com.codecool.firstaidcompetition.model.User;
import com.codecool.firstaidcompetition.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by keli on 2017.09.07..
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
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
    }

    public User authenticateUser(String username, String password) {
        User userByUsername = userRepository.findByUserName(username);
        Set<User> userByPassword = authenticateByPassword(password);
        if (userByUsername != null && userByPassword != null){
            for (User user : userByPassword) {
                if (user.getId() == userByUsername.getId()) {
                    return user;
                }
            }
        }
        return null;
    }

    private Set<User> authenticateByPassword(String password) {
        Iterator<User> users = userRepository.findAll().iterator();
        Set<User> userWithSamePass = new HashSet<>();   // pass can be the same, have to check it
        while (users.hasNext()){
            User currentUser = users.next();
            if (bCryptPasswordEncoder.matches(password, currentUser.getPassword())){
                userWithSamePass.add(currentUser);
            }
        }
        return userWithSamePass;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}