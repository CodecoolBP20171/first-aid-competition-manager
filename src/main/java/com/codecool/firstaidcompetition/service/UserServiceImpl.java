package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.model.Role;
import com.codecool.firstaidcompetition.model.User;
import com.codecool.firstaidcompetition.repository.RoleRepository;
import com.codecool.firstaidcompetition.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void saveUser(User user, String userRole){
        Set<Role> roles = authenticateUserRole(userRole);   // authenticate and set role
        user.setRole(roles);

        String currentPass = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(currentPass));
        userRepository.save(user);
    }

    public Set<Role> authenticateUserRole(String userRole){
        Role role = roleRepository.findByRole(userRole);
        Set<Role> rolesSet = new HashSet<>();
        if (role != null){
            rolesSet.add(role);
            return rolesSet;
        }
        return rolesSet;
    }

    public boolean checkUsernameAlreadyExists(String username){
        User user = userRepository.findByUserName(username);
        if (user == null){
            return false;
        }
        return true;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImpl(user);
    }
}
