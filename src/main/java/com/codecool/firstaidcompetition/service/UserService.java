package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.model.User;

/**
 * Created by keli on 2017.09.03..
 */
public interface UserService {
    User findByUsername(String username);
    User findByPassword(String password);
}
