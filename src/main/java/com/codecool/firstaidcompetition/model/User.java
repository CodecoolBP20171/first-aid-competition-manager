package com.codecool.firstaidcompetition.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "password", length = 15)
    private String password;

    @OneToMany (mappedBy = "owner")
    private List<Competition> competitions = new ArrayList<>();

    @ManyToOne
    private Role role;

    public User(){}

    public User(String fullName, String userName, String email, String password, Role role) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
