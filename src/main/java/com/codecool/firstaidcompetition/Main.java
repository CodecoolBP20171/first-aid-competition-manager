package com.codecool.firstaidcompetition;

import com.codecool.firstaidcompetition.Model.Team;
import com.codecool.firstaidcompetition.Model.util.TeamCategory;

import javax.persistence.*;

public class Main {



    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("first-aid-competition");
        EntityManager em = emf.createEntityManager();

        // example team
        Team team = new Team("Csapatnév", 2, 0456, TeamCategory.CHILD, 1);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(team);
//        em.persist(TeamCategory.CHILD);
        transaction.commit();
        System.out.println("ende");

    }
}