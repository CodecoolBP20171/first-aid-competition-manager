package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.Exercise;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.repository.CompetitionRepository;
import com.codecool.firstaidcompetition.repository.StationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class StationServiceTest {

    @Autowired
    private StationService stationService;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private CompetitionRepository competitionRepository;
    private Competition competition;
    private Station newStation1;
    private Station newStation2;
    private Exercise exercise;

    @Before
    public void updateDb() {
        this.competition = new Competition("Teszt verseny", "Valamilyen hely", "2025-05-25", null);
        Mockito.when(competitionRepository.findOne(1L))
                .thenReturn(competition);

        this.newStation1 = new Station("Teszt 1.0", 44
                , "Teszt leírás 1.0", competition);
        this.newStation2 = new Station("Teszt 2.0", 55,
                "Teszt leírás 2.0", competition);

        Mockito.when(stationRepository.findOne(1L)).thenReturn(newStation1);
        this.exercise = new Exercise("Exercise 1.0", "Exszerszájz");
    }

    @Test
    public void save_SaveStation_IfAddOneStation() {
        System.out.println(stationService.findById(1L).getName());

        Station station = new Station("Teszt 3.0", 44, "Teszt leírás 3.0",
                competition);
        stationService.save(station);

        Station addedStation = stationRepository.findOne(station.getId());
//        long size = stationRepository.findAll().spliterator().getExactSizeIfKnown();

//        assertEquals(3, size);
        assertEquals(newStation1, addedStation);
//        assertEquals(station.getDescription(), addedStation.getDescription());
//        assertEquals(station.getNumber(), addedStation.getNumber());
//        assertEquals(station.getExercises(), addedStation.getExercises());
    }

    // https://dzone.com/articles/junit-testing-spring-mvc-0
    @Configuration
    static class StationServiceTestContextConfiguration {
        @Bean
        StationRepository stationRepository() {
            return Mockito.mock(StationRepository.class);
        }

        @Bean
        CompetitionRepository competitionRepository() {
            return Mockito.mock(CompetitionRepository.class);
        }

        @Bean
        CompetitionService competitionService() {
            return Mockito.mock(CompetitionService.class);
        }

        @Bean
        StationService stationService() {
            return new StationService(stationRepository(), competitionService());
        }
    }

}
