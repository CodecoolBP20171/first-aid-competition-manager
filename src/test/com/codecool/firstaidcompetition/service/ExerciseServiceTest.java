package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.model.Exercise;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.repository.ExerciseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExerciseServiceTest {

    @InjectMocks
    private ExerciseService exerciseService;

    @Mock
    private ExerciseRepository exerciseRepository;

    private Exercise exercise1;
    private Exercise exercise2;
    private List<Exercise> exerciseList;

    @Before
    public void setup() {
        Station station = new Station("station", 1, "station's description", null);
        exercise1 = new Exercise("1.0. exercise", "1.0. description", station);
        exercise2 = new Exercise("2.0. exercise", "2.0. description", station);
        exerciseList = Arrays.asList(exercise1, exercise2);

        when(exerciseRepository.findAll()).thenReturn(exerciseList);
        when(exerciseRepository.findOne(1L)).thenReturn(exercise1);
        when(exerciseRepository.findOne(2L)).thenReturn(exercise2);
    }

    @Test
    public void update_WhenUpdate_ThenReturnNothing() {
        when(exerciseRepository.save(exercise1)).thenReturn(null);

        exercise1.setName("changed");
        exerciseService.update(exercise1.getId(), exercise1);
    }

    @Test
    public void save_WhenSave_ThenReturnNothing() {
        when(exerciseRepository.save(exercise2)).thenReturn(null);
        exerciseService.save(exercise2);
    }

    @Test
    public void delete_WhenDeleteExercise_ThenReturnNothing() {
        doNothing().when(exerciseRepository).delete(1L);
        exerciseService.delete(1L);
    }

    @Test
    public void findById_WhenGetNonExistingId_ThenReturnNull() {
        assertNull(exerciseService.findById(5L));
        assertNull(exerciseService.findById(1500L));
    }

    @Test
    public void findById_WhenGetExerciseById_ThenReturnOneExercise() {
        assertEquals(exercise1, exerciseService.findById(1L));
    }

    @Test
    public void findAll_WhenFindExercises_ThenReturnWithList() {
        assertTrue(exerciseService.findAll() != null);
        assertEquals(exerciseList, exerciseService.findAll());
    }

}
