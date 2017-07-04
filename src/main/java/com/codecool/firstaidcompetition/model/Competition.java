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

    @Column(name = "owner_id")
    private int ownerId;

    @OneToMany(mappedBy = "competitionID")
    private Set<Station> stations = new HashSet<>();

    public Competition() {

    }

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
