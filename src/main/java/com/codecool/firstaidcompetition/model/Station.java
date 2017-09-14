package com.codecool.firstaidcompetition.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "findAllStations", query = "select stat from stations stat"),
        @NamedQuery(name = "findStationById",
                query = "select stat from stations stat where stat.id = :stationId"),
        @NamedQuery(name = "findStationByName",
                query = "select stat from stations stat where stat.name = :stationName"),
        @NamedQuery(name = "findStationByNumber",
                query = "select stat from stations stat where stat.number = :stationNumber"),
})
@Entity(name = "stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String name;

    @Column
    private int number;

    @Column(length = 1000)
    private String description;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @JsonIgnore
    @OneToMany(mappedBy = "station", cascade = CascadeType.REMOVE)
    private List<Exercise> exercises = new ArrayList<>();

    public Station() {
    }

    public Station(String name, int number, String description, Competition competition) {
        this.name = name;
        this.number = number;
        this.description = description;
        this.competition = competition;
    }

    public Station(String name, int number, String description, Competition competition, List<Exercise> exercises) {
        this.name = name;
        this.number = number;
        this.description = description;
        this.competition = competition;
        this.exercises = exercises;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competitionID) {
        this.competition = competitionID;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercise(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void addExercise(Exercise exercise){
        exercises.add(exercise);
    }
}
