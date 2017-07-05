package com.codecool.firstaidcompetition;

import com.codecool.firstaidcompetition.model.*;

import javax.persistence.*;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("first-aid-competition");
        EntityManager em = emf.createEntityManager();

        // example
        Competition competition = new Competition("verseny", "Budapest", new Date(), 2);
        Station station = new Station("allomas", 12, "egyik allomas", competition);

        User user = new User("fullname", "username", "email", "pass", competition);

        Task task = new Task("task name", 10);
        SubTask subTask = new SubTask("subtaskname", (short) 12);
        Team team = new Team("Csapatnév", 2, 0456, TeamCategory.CHILD, competition);
        TeamResult teamResult = new TeamResult(12, team);

        Protest protest = new Protest(team, task, "óvási leírás", "döntés megszületett");


        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(station);
        em.persist(user);
        em.persist(task);
        em.persist(subTask);
        em.persist(competition);

        em.persist(team);
        em.persist(teamResult);

        em.persist(protest);
        transaction.commit();
        System.out.println("ende");

        em.close();
        emf.close();

    }
}