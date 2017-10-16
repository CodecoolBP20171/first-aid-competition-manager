package com.codecool.firstaidcompetition.repository;

import com.codecool.firstaidcompetition.model.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by keli on 2017.09.03..
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String role);
}
