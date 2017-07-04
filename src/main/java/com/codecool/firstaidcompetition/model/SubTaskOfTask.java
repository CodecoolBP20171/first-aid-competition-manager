package com.codecool.firstaidcompetition.model;

import javax.persistence.*;

@Entity(name = "sub_task_of_tasks")
public class SubTaskOfTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int score;

    public SubTaskOfTask() {
    }

    public SubTaskOfTask(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
