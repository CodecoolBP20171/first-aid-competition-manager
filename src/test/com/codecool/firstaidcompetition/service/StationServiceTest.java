package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.Exercise;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.repository.CompetitionRepository;
import com.codecool.firstaidcompetition.repository.StationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

// Helper: https://stackoverflow.com/questions/37727311/how-do-i-junit-test-a-spring-autowired-constructor
@RunWith(MockitoJUnitRunner.class)
public class StationServiceTest {

    @InjectMocks private StationService stationService;

    @Mock private StationRepository stationRepository;
    @Mock private CompetitionRepository competitionRepository;

    private Station newStation1;
    private Station newStation2;
    private List<Station> stationList;

    private Competition competition;
    private Exercise exercise;

    @Before
    public void updateDb() {
        competition = new Competition("Teszt verseny", "Valamilyen hely", "2025-05-25", null);

        newStation1 = new Station("Teszt 1.0", 44,
                "Teszt leírás 1.0", competition);
        newStation2 = new Station("Teszt 2.0", 55,
                "Teszt leírás 2.0", competition);
        stationList = Arrays.asList(newStation1, newStation2);

        Mockito.when(stationRepository.findAll()).thenReturn(stationList);
        Mockito.when(stationRepository.findOne(1L)).thenReturn(newStation1);

        exercise = new Exercise("Exercise 1.0", "Exszerszájz");
    }

    @Test
    public void findStation_WhenGetById_ThenReturnOneStation() {
        assertEquals(newStation1, stationService.findById(1L));
    }

    @Test
    public void findStations_WhenFindAll_ThenReturnWithList() {
        assertEquals(stationList, stationService.findAll());
    }

}
