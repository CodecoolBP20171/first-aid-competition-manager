package com.codecool.firstaidcompetition.model;

import javax.persistence.*;

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
    private int id;

    @Column(length = 50)
    private String name;

    @Column
    private int number;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    private Competition competition;

    @ManyToOne
    private Task task;

    public Station() {
    }

    public Station(String name, int number, String description, Competition competition) {
        this.name = name;
        this.number = number;
        this.description = description;
        this.competition = competition;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competitionID) {
        this.competition = competitionID;
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
