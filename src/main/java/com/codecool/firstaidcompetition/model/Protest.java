package com.codecool.firstaidcompetition.model;

import javax.persistence.*;

/**
 * Created by keli on 2017.07.04..
 */

@Entity(name = "protests")
public class Protest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Team team;

    @OneToOne
    private Task task;

    @Column
    private String justification;

    @Column
    private String decision;


    public Protest(){}

    public Protest(Team team, Task task, String justification, String decision) {
        this.team = team;
        this.task = task;
        this.justification = justification;
        this.decision = decision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}
