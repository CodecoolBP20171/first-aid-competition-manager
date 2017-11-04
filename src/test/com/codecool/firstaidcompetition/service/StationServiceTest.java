package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.repository.StationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

// Helper: https://stackoverflow.com/questions/37727311/how-do-i-junit-test-a-spring-autowired-constructor
@RunWith(MockitoJUnitRunner.class)
public class StationServiceTest {

    @InjectMocks // mock autowired constructor
    private StationService stationService;

    @Mock
    private StationRepository stationRepository;

    private Station newStation1;
    private Station newStation2;
    private List<Station> stationList;

    private Competition competition;

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
    }

    @Test
    public void update_WhenUpdate_ThenReturnNothing() {
        when(stationRepository.save(newStation2)).thenReturn(null);

        newStation2.setName("changed");
        stationService.update(newStation2.getId(), newStation2);
    }

    @Test
    public void save_WhenSave_ThenReturnNothing() {
        when(stationRepository.save(newStation2)).thenReturn(null);
        stationService.save(newStation2);
    }

    @Test
    public void delete_WhenDelete_ThenReturnNothing() {
        doNothing().when(stationRepository).delete(1L);
        stationService.delete(1L);
    }

    @Test
    public void findById_WhenGetNonExistingId_ThenReturnNull() {
        assertNull(stationService.findById(5L));
        assertNull(stationService.findById(1500L));
    }

    @Test
    public void findById_WhenGetStationById_ThenReturnOneStation() {
        assertEquals(newStation1, stationService.findById(1L));
    }

    @Test
    public void findAll_WhenFindStations_ThenReturnWithList() {
        assertTrue(stationService.findAll() != null);
        assertEquals(stationList, stationService.findAll());
    }

}
