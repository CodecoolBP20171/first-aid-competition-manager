package com.codecool.firstaidcompetition.Model;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.Max;

/**
 * Created by keli on 2017.07.04..
 */
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
    private int category;

    @Column
    @OneToOne
    private int competitionId;

    public Team(){}

    public Team(String name, int teamNumber, int pinCode, int category, int competitionId) {
        this.name = name;
        this.teamNumber = teamNumber;
        this.pinCode = pinCode;
        this.category = category;
        this.competitionId = competitionId;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }
}

