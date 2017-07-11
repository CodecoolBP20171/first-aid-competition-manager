package com.codecool.firstaidcompetition.model;

import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.model.Team;
import com.codecool.firstaidcompetition.model.User;

import javax.persistence.*;
//import javax.validation.constraints.Max;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity(name = "competitions")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String name;

    private String location;

    @Column(name = "date_of_event")
    @Temporal(TemporalType.DATE)
    private Date dateOfEvent;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;

    @OneToMany(mappedBy = "competition")
    private List<Station> stations = new ArrayList<>();

    @OneToMany(mappedBy = "competition")
    private Set<Team> team = new HashSet<>();

    public Competition() {}

    public Competition(String name, String location, String dateOfEvent, User owner) {
        this.name = name;
        this.location = location;

        // Is it OK?
        this.dateOfEvent = convertStringToDate(dateOfEvent);
        this.owner = owner;
    }

    public Date convertStringToDate(String dateOfEvent){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            Date date = simpleDateFormat.parse(dateOfEvent);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setDateOfEvent(String dateOfEvent) {
        this.dateOfEvent = convertStringToDate(dateOfEvent);
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

    public void addStation(Station station){
        this.stations.add(station);
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public Set<Team> getTeam() {
        return team;
    }

    public void setTeam(Set<Team> team) {
        this.team = team;
    }
}
