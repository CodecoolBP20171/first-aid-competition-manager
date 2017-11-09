package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.repository.CompetitionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

// Helper: https://stackoverflow.com/questions/37727311/how-do-i-junit-test-a-spring-autowired-constructor
@RunWith(MockitoJUnitRunner.class)
public class CompetitionServiceTest {

    @InjectMocks // mock autowired constructor
    private CompetitionService competitionService;

    @Mock
    private CompetitionRepository competitionRepository;

    private Competition competition1;
    private Competition competition2;
    private List<Competition> competitionList;

    @Before
    public void updateDb() {
        competition1 = new Competition("Teszt verseny 1", "Budapest", "2018-05-25", null);
        competition2 = new Competition("Teszt verseny 2", "Miskolc", "2020-06-15", null);
        competitionList = Arrays.asList(competition1, competition2);

        when(competitionRepository.findAll()).thenReturn(competitionList);
        when(competitionRepository.findOne(1L)).thenReturn(competition1);
    }

    @Test
    public void update_WhenUpdate_ThenReturnNothing() {
        when(competitionRepository.save(competition2)).thenReturn(null);

        competition2.setName("changed");
        competitionService.editCompetitionService(competition2.getId(), competition2);
    }

    @Test
    public void save_WhenSave_ThenReturnNothing() {
        when(competitionRepository.save(competition2)).thenReturn(null);
        competitionService.save(competition2);
    }

    @Test
    public void delete_WhenDelete_ThenReturnNothing() {
        doNothing().when(competitionRepository).delete(1L);
        competitionService.deleteCompetitionService(1L);
    }

    @Test
    public void findById_WhenGetNonExistingId_ThenReturnNull() {
        assertNull(competitionService.getCompetitionByIdService(5L));
        assertNull(competitionService.getCompetitionByIdService(1500L));
    }

    @Test
    public void findById_WhenGetCompetitionById_ThenReturnOneCompetition() {
        assertEquals(competition1, competitionService.getCompetitionByIdService(1L));
    }

    @Test
    public void findAll_WhenFindCompetitions_ThenReturnWithList() {
        assertTrue(competitionService.getAllCompetitionsService() != null);
        assertEquals(competitionList, competitionService.getAllCompetitionsService());
    }

}
