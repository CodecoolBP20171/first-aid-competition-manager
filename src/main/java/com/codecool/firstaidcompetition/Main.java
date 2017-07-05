package com.codecool.firstaidcompetition;

import com.codecool.firstaidcompetition.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.*;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.*;
import org.hibernate.query.Query;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
        System.out.println("End");

        //Named queries
        List<Team> teams = em.createNamedQuery("selectAllTeams", Team.class).getResultList();
        List<Team> teamsById = em.createNamedQuery("selectTeamsById", Team.class).setParameter("id", 3).getResultList();
        List<Team> teamsByName = em.createNamedQuery("selectTeamsByName", Team.class).setParameter("name", "Csapatnév").getResultList();
        List<Team> teamsByPin = em.createNamedQuery("selectTeamsByPinCode", Team.class).setParameter("pin_code", 302).getResultList();
        List<Team> teamsByTeamNumber = em.createNamedQuery("selectTeamsByTeamNumber", Team.class).setParameter("team_number", 2).getResultList();

        List<TeamResult> teamResults = em.createNamedQuery("queryTeamResults", TeamResult.class).getResultList();

        em.close();
        emf.close();
    }
}