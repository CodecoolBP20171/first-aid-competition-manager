package com.codecool.firstaidcompetition;

import com.codecool.firstaidcompetition.model.*;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("first-aid-competition");
        EntityManager em = emf.createEntityManager();

        // example
        User user = new User("fullname", "username", "email", "pass");

        // Date formatting example
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse("2012-07-08");

        Competition competition = new Competition("verseny", "Budapest", date, user);
        Station station = new Station("allomas", 12, "egyik allomas", competition);


        Exercise exercise = new Exercise("exercise name", "loooooooooooooooooong description");
        Task task = new Task("subtaskname", 12L);
        exercise.addTask(task);

        Team team = new Team("Csapatnév", 2, 0456, TeamCategory.CHILD, competition);
        TeamResult teamResult = new TeamResult(12, team);

        Protest protest = new Protest(team, exercise, "óvási leírás", "döntés megszületett");


        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(station);
        em.persist(user);
        em.persist(exercise);
        em.persist(task);
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

        List<TeamResult> teamResults = em.createNamedQuery("selectAllTeamResults", TeamResult.class).getResultList();
        List<TeamResult> teamResultsById = em.createNamedQuery("selectTeamResultsByID", TeamResult.class).
                setParameter("id", 1).getResultList();
        List<TeamResult> teamResultsByScore = em.createNamedQuery("selectTeamResultsByScore", TeamResult.class).
                setParameter("result_score", 12).getResultList();

        em.close();
        emf.close();
    }
}