package com.codecool.firstaidcompetition.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String name;

    @Column
    private int score;

    @OneToOne(mappedBy = "task")
    private Protest protest;

    public Task() {
    }

    public Task(String name, int score, Protest protest) {
        this.name = name;
        this.score = score;
        this.protest = protest;
    }

    public Protest getProtest() {
        return protest;
    }

    public void setProtest(Protest protest) {
        this.protest = protest;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
