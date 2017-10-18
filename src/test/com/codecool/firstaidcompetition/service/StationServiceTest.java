package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.Exercise;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.repository.CompetitionRepository;
import com.codecool.firstaidcompetition.repository.StationRepository;
import org.hibernate.service.spi.InjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class StationServiceTest {

    // http://www.baeldung.com/spring-boot-testing
//    @TestConfiguration
//    static class StationServiceTestContextConfiguration {
//        @Bean
//        StationService stationService() {
//            return new StationService(Mockito.mock(StationRepository.class),
//                    Mockito.mock(CompetitionService.class));
//        }
//    }

    @Autowired
    private StationService stationService;
    @MockBean
    private StationRepository stationRepository;
    @MockBean
    private CompetitionRepository competitionRepository;

    private Competition competition;
    private Station newStation1;
    private Station newStation2;
    private Exercise exercise;

    @Before
    public void setup() {
        updateDb();
    }

    public void updateDb() {
        this.competition = new Competition("Teszt verseny", "Valamilyen hely", "2025-05-25", null);
        competitionRepository.save(competition);
        Mockito.when(competitionRepository.findOne(competition.getId()))
                .thenReturn(competition);

        this.newStation1 = new Station("Teszt 1.0", 44, "Teszt leírás 1.0", competition);
        this.newStation2 = new Station("Teszt 2.0", 55, "Teszt leírás 2.0", competition);
        stationRepository.save(newStation1);
        Mockito.when(stationRepository.findOne(newStation1.getId()))
                .thenReturn(newStation1);

        this.exercise = new Exercise("Exercise 1.0", "Exszerszájz");
    }

    @Test
    public void save_SaveStation_IfAddOneStation() {
        System.out.println(stationRepository.findOne(1L).getName());
//        stationService.findAll().forEach(s -> System.out.println(s.getName()));

        //        assertThat(found.getName()).
//        stationService.findById(1L).getName());

//        Station station = new Station("Teszt 3.0", 44, "Teszt leírás 3.0",
//                competition, Arrays.asList(exercise));
//        stationService.save(station);
//
//        Station addedStation = stationRepository.findOne(station.getId());
//        long size = stationRepository.findAll().spliterator().getExactSizeIfKnown();
//
//        assertEquals(3, size);
//        assertEquals(competition.getName(), addedStation.getCompetition().getName());
//        assertEquals(station.getDescription(), addedStation.getDescription());
//        assertEquals(station.getNumber(), addedStation.getNumber());
//        assertEquals(station.getExercises(), addedStation.getExercises());
    }

}
