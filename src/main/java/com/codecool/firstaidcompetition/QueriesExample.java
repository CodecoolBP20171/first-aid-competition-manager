package com.codecool.firstaidcompetition;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.Protest;
import com.codecool.firstaidcompetition.model.Station;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

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

        Query query2 = em.createNamedQuery("findCompetitionById", Competition.class).
                setParameter("competitionId", 1);
        List<Competition> compById = query2.getResultList();
        System.out.println(compById.get(0).getId());

        List<Competition> compByLocation = em.createNamedQuery("findCompetitionByLocation", Competition.class).
                setParameter("competitionLocation", "Budapest").getResultList();
        System.out.println(compByLocation.get(0).getLocation());


        List<Protest> protests = em.createNamedQuery("findAllProtests",
                Protest.class).getResultList();
        System.out.println(protests.get(0).getTeam().getName());

        List<Protest> protestsByTeamId = em.createNamedQuery("findProtestByTeamId",
                Protest.class).setParameter("teamId", 1).getResultList();
        System.out.println(protests.get(0).getDecision());


        System.out.println(em.createNamedQuery("findAllStations",
                Station.class).getResultList().get(0));
        System.out.println(em.createNamedQuery("findStationById",
                Station.class).setParameter("stationId", 1).getSingleResult().getName());

    }
}
