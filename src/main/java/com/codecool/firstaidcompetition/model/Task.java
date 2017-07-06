package com.codecool.firstaidcompetition.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "findAllTask", query = "select task from tasks task"),
        @NamedQuery(name = "findTaskById", query = "select task from tasks task where task.id = :taskId")
})
@Entity(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String name;

    @Column
    private long score;

    @Column
    private boolean defaultTaks;

    @OneToOne
    private Task prerequisiteTask;

    @ManyToMany(mappedBy = "tasks")
    private Set<Exercise> exercises = new HashSet<>();

    public Task() {}

    public Task(String name) {
        this.name = name;
        this.score = 1;
    }

    public Task(String name, long score){
        this.name = name;
        this.score = score;
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

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public Task getPrerequisiteTask() {
        return prerequisiteTask;
    }

    public void setPrerequisiteTask(Task prerequisiteTask) {
        this.prerequisiteTask = prerequisiteTask;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }
}
