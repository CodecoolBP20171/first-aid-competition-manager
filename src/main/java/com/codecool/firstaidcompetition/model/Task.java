package com.codecool.firstaidcompetition.model;

import javax.persistence.*;

@Entity(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String name;

    @Column
    private int score;

    public Task() {
    }

    public Task(String name, int score) {
        this.name = name;
        this.score = score;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
