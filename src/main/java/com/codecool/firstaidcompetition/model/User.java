package com.codecool.firstaidcompetition.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@NamedQueries({
//        @NamedQuery(name = "findAllUsers", query = "select user from users user"),
//        @NamedQuery(name = "findUserById", query = "select user from users user where user.id = :userId"),
//        @NamedQuery(name = "findUserByFullName", query = "select user from users user where user.fullName = :userFullName"),
//        @NamedQuery(name = "findUserByUserName", query = "select user from users user where user.userName = :userUserName"),
//        @NamedQuery(name = "findUserByEmail", query = "select user from users user where user.email = :userEmail")
//})
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", length = 50)
    private String fullName;

    @Column(name = "user_name", length = 15)
    private String userName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @OneToMany (mappedBy = "owner")
    private List<Competition> competitions = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(){}

    public User(String fullName, String userName, String email, String password, List<Competition> competitions, Set<Role> roles) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.competitions = competitions;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void addCompetition(Competition competition) {
        this.competitions.add(competition);
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

    public Set<Role> getRole() {
        return roles;
    }

    public void setRole(Set<Role> roles) {
        this.roles = roles;
    }
}
