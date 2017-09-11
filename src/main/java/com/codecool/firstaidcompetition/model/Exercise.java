package com.codecool.firstaidcompetition.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "findAllExercise", query = "select exer from exercise exer"),
        @NamedQuery(name = "findExerciseById", query = "select exer from exercise exer where exer.id = :exerId"),
})
@Entity(name = "exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String name;

    @Column(length = 1000)
    private String description;

    @OneToOne(mappedBy = "exercise", cascade = CascadeType.REMOVE)
    private Protest protest;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.REMOVE)
    private List<Station> stations = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name="tasks_of_exercises",
            joinColumns=@JoinColumn(name="exercise_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="task_id", referencedColumnName="id"))
    private List<Task> tasks = new ArrayList<>();

    public Exercise() {}

    public Exercise(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Exercise(String name, String description, List<Station> stations) {
        this.name = name;
        this.description = description;
        this.stations = stations;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Protest getProtest() {
        return protest;
    }

    public void setProtest(Protest protest) {
        this.protest = protest;
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

}
