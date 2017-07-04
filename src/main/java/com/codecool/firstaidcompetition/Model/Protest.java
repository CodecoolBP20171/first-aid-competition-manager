package com.codecool.firstaidcompetition.Model;

import javax.persistence.*;

/**
 * Created by keli on 2017.07.04..
 */

@Entity(name = "protests")
public class Protest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "team_id")
    private Team teamId;

    @Column(name = "task_id")
    private Task taskId;

    @Column
    private String justification;

    @Column
    private String decision;

    public Protest(){}

    public Protest(Team teamId, Task taskId, String justification, String decision) {
        this.teamId = teamId;
        this.taskId = taskId;
        this.justification = justification;
        this.decision = decision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeamId() {
        return teamId;
    }

    public void setTeamId(Team teamId) {
        this.teamId = teamId;
    }

    public Task getTaskId() {
        return taskId;
    }

    public void setTaskId(Task taskId) {
        this.taskId = taskId;
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
