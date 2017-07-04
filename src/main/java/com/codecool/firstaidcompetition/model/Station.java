package com.codecool.firstaidcompetition.model;

import javax.persistence.*;

@Entity(name = "stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String name;

    @Column
    private int number;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    private Competition competitionID;

    public Station() {
    }

    public Station(String name, int number, String description, Competition competitionID) {
        this.name = name;
        this.number = number;
        this.description = description;
        this.competitionID = competitionID;
    }

    public Competition getCompetitionID() {
        return competitionID;
    }

    public void setCompetitionID(Competition competitionID) {
        this.competitionID = competitionID;
    }

    public int getId() {
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
}
