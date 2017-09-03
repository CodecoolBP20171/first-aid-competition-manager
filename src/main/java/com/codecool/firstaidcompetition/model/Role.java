package com.codecool.firstaidcompetition.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    private long id;

    private String role;

    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();

    public Role(){}

    public Role(String role, Set<User> users) {
        this.role = role;
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
