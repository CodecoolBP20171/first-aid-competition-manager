package com.codecool.firstaidcompetition;

import com.codecool.firstaidcompetition.model.Competition;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by keli on 2017.07.05..
 */
public class QueriesExample {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("first-aid-competition");
        EntityManager em = emf.createEntityManager();

        Query query = em.createNamedQuery("findAllCompetitions",
                Competition.class);
        List<Competition> a = query.getResultList();
        for (Competition competition1 : a) {
            System.out.println(competition1.getName());
        }
    }
}
