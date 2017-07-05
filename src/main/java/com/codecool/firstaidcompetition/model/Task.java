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
    private int score;

    @OneToOne(mappedBy = "task")
    private Protest protest;

    @OneToMany(mappedBy = "task")
    private Set<Station> stations = new HashSet<>();

    @ManyToMany
    @JoinTable(name="sub_task_of_tasks")
    private Set<SubTask> subTasks = new HashSet<>();

    public Task() {}

    public Task(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Set<Station> getStations() {
        return stations;
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }

    public Set<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(Set<SubTask> subTasks) {
        this.subTasks = subTasks;
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
