package com.codecool.firstaidcompetition.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "selectAllTeamResults", query = "select teamResult from team_results teamResult"),
        @NamedQuery(name = "selectTeamResultsByID", query = "select teamResult from team_results teamResult " +
                "where teamResult.id = :id"),
        @NamedQuery(name = "selectTeamResultsByScore", query = "select teamResult from team_results teamResult " +
                "where teamResult.resultScore = :result_score")
})
@Entity(name = "team_results")
@NamedQuery(name = "queryTeamResults", query = "SELECT teamResult from team_results teamResult")
public class TeamResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "result_score")
    private int resultScore;

    @JsonIgnore
    @OneToOne
    private Team team;

    public TeamResult() {}

    public TeamResult(int resultScore, Team team) {
        this.resultScore = resultScore;
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResultScore() {
        return resultScore;
    }

    public void setResultScore(int resultScore) {
        this.resultScore = resultScore;
    }
}
