package com.codecool.firstaidcompetition.model;

import javax.persistence.*;

@Entity(name = "team_results")
public class TeamResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "result_score")
    private short resultScore;

    public TeamResult() {
    }

    public TeamResult(short resultScore) {
        this.resultScore = resultScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getResultScore() {
        return resultScore;
    }

    public void setResultScore(short resultScore) {
        this.resultScore = resultScore;
    }
}
