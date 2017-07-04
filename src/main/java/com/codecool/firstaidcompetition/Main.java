package com.codecool.firstaidcompetition;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.model.Team;
import com.codecool.firstaidcompetition.model.TeamCategory;

import javax.persistence.*;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("first-aid-competition");
        EntityManager em = emf.createEntityManager();

        Competition competition = new Competition("verseny", "Budapest", new Date(), 2);
        Station station = new Station("allomas", 12, "egyik allomas", competition);

        // example team
//        Team team = new Team("Csapatnév", 2, 0456, TeamCategory.CHILD, 1);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
//        em.persist(team);
        em.persist(competition);
        em.persist(station);
//        em.persist(TeamCategory.CHILD);
        transaction.commit();
        System.out.println("ende");

    }
}