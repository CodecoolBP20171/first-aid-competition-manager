package com.codecool.firstaidcompetition.model;

import javax.persistence.*;
//import javax.validation.constraints.Max;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "findAllCompetitions", query = "select comp from competitions comp"),
        @NamedQuery(name = "findCompetitionById", query = "select comp from competitions comp where comp.id = :competitionId"),
        @NamedQuery(name = "findCompetitionByLocation", query = "select comp from competitions comp where comp.location = :competitionLocation"),
        @NamedQuery(name = "findCompetitionByName", query = "select comp from competitions comp where comp.name = :competitionName")
//        @NamedQuery(name = "findCompetitionByDate", query = "select comp from competitions comp where comp.name = :competitionName")
})
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

    @Column(name = "owner_id")
    private int ownerId;

    @OneToMany(mappedBy = "competition")
    private Set<Station> stations = new HashSet<>();

    @OneToMany(mappedBy = "competition")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "competition")
    private Set<Team> team = new HashSet<>();

    public Competition() {}

    public Competition(String name, String location, Date dateOfEvent, int ownerId) {
        this.name = name;
        this.location = location;
        this.dateOfEvent = dateOfEvent;
        this.ownerId = ownerId;
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

    public int getOwnerId() {
        return ownerId;
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

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
