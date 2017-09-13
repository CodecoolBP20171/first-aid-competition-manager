package com.codecool.firstaidcompetition.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by keli on 2017.07.04..
 */
@NamedQueries({
        @NamedQuery(name = "selectAllTeams", query = "SELECT team from teams team"),
        @NamedQuery(name = "selectTeamsById", query = "SELECT team from teams team where team.id = :id"),
        @NamedQuery(name = "selectTeamsByName", query = "SELECT team from teams team where team.name = :name"),
        @NamedQuery(name = "selectTeamsByPinCode", query = "SELECT team from teams team where team.pinCode = :pin_code"),
        @NamedQuery(name = "selectTeamsByTeamNumber", query = "SELECT team from teams team where team.teamNumber = :team_number")
})
@Entity(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String name;

    @Column(name = "team_number")
    private int teamNumber;

    @Column(name = "pin_code")
    private int pinCode;

    @Enumerated(EnumType.STRING)
    private TeamCategory category;

    @JsonIgnore
    @OneToOne(mappedBy = "team")
    private Protest protest;

    @JsonIgnore
    @ManyToOne
    private Competition competition;

    @JsonIgnore
    @OneToOne(mappedBy = "team")
    private TeamResult teamResult;

    public Team(){}

    public Team(String name, int teamNumber, int pinCode, TeamCategory category, Competition competition) {
        this.name = name;
        this.teamNumber = teamNumber;
        this.pinCode = pinCode;
        this.category = category;
        this.competition = competition;
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

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public TeamCategory getCategory() {
        return category;
    }

    public void setCategory(TeamCategory category) {
        this.category = category;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competitionId) {
        this.competition = competitionId;
    }
}

