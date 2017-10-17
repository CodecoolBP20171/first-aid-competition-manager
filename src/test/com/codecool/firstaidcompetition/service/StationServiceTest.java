package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.Application;
import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.repository.CompetitionRepository;
import com.codecool.firstaidcompetition.repository.StationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class StationServiceTest {

    @Autowired private StationService stationService;
    @Autowired private StationRepository stationRepository;
    @Autowired private CompetitionRepository competitionRepository;

    private Competition competition;
    private Station newStation1;
    private Station newStation2;

    @Before
    public void setup() {
        stationRepository.deleteAll();
        competitionRepository.deleteAll();

        updateDb();
    }

    public void updateDb() {
        this.competition = new Competition("Teszt verseny", "Valamilyen hely", "2025-05-25", null);
        competitionRepository.save(competition);

        this.newStation1 = new Station("Teszt 1.0", 44, "Teszt leírás 1.0", competition);
        this.newStation2 = new Station("Teszt 2.0", 55, "Teszt leírás 2.0", competition);
        stationRepository.save(newStation1);
        stationRepository.save(newStation2);
    }

    @Test
    public void save_SaveStation_IfAddOneStation() {
        Station station = new Station("Teszt 3.0", 44, "Teszt leírás 3.0", competition);
        stationService.save(station);

        long size = stationRepository.findAll().spliterator().getExactSizeIfKnown();
        assertEquals(3, size);
    }

}
