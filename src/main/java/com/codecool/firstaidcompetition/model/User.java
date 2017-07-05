package com.codecool.firstaidcompetition.model;

import javax.persistence.*;

/**
 * Created by keli on 2017.07.04..
 */
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

    @ManyToOne
    private Competition competition;

    public User(){}

    public User(String fullName, String userName, String email, String password, Competition competition) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.competition = competition;
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

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

}
