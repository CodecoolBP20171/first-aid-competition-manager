package com.codecool.firstaidcompetition.repository;

import com.codecool.firstaidcompetition.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by keli on 2017.07.06..
 */
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUserName(String username);
}
