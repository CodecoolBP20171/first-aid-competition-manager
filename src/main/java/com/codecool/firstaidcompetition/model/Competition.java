package com.codecool.firstaidcompetition.model;

import javax.persistence.*;
//import javax.validation.constraints.Max;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity(name = "competitions")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String name;
    private String location;

    @Column(name = "date_of_event")
    private Date dateOfEvent;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;

    @OneToMany(mappedBy = "competition")
    private Set<Station> stations = new HashSet<>();


    @OneToMany(mappedBy = "competition")
    private Set<Team> team = new HashSet<>();

    public Competition() {}

    public Competition(String name, String location, Date dateOfEvent, User owner) {
        this.name = name;
        this.location = location;
        this.dateOfEvent = dateOfEvent;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Date getDateOfEvent() {
        return dateOfEvent;
    }

    public User getOwner() {
        return owner;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDateOfEvent(Date dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

}
