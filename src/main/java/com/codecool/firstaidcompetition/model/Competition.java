package com.codecool.firstaidcompetition.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity(name = "competitions")
public class Competition {
    private static final Logger logger = LoggerFactory.getLogger(Competition.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String name;

    private String location;

    @Column(name = "date_of_event")
    @Temporal(TemporalType.DATE)
    private Date dateOfEvent;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "owner_id")
    private User owner;

    @JsonBackReference
    @OneToMany(mappedBy = "competition", cascade = CascadeType.REMOVE)
    private List<Station> stations = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Team> team = new HashSet<>();

    public Competition() {
    }

    public Competition(String name, String location, String dateOfEvent, User owner) {
        this.name = name;
        this.location = location;
        this.dateOfEvent = convertStringToDate(dateOfEvent);
        this.owner = owner;
    }

    public Date convertStringToDate(String dateOfEvent) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            Date date = simpleDateFormat.parse(dateOfEvent);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            logger.warn("Can't convert string dateOfEvent [{}] to date format", dateOfEvent);
            return null;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDateOfEvent() {
        return dateOfEvent;
    }

    public void setDateOfEvent(String dateOfEvent) {
        this.dateOfEvent = convertStringToDate(dateOfEvent);
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void addStation(Station station) {
        this.stations.add(station);
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
