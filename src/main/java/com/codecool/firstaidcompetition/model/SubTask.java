package com.codecool.firstaidcompetition.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "findAllSubTask", query = "select stask from sub_task stask"),
        @NamedQuery(name = "findSubTaskById", query = "select stask from sub_task stask where stask.id = :staskId"),
})

@Entity(name = "sub_task")
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String name;

    @Column
    private long score;

    @ManyToMany(mappedBy = "subTasks")
    private Set<Task> tasks = new HashSet<>();

    public SubTask() {}

    public SubTask(String name, long score) {
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

    public void setScore(short score) {
        this.score = score;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
