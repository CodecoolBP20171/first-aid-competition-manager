package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.model.User;

/**
 * Created by keli on 2017.09.03..
 */
public interface UserService {
    public User findByUsername(String username);
}
