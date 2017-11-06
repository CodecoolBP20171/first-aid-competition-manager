package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.Application;
import com.codecool.firstaidcompetition.model.Exercise;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.repository.ExerciseRepository;
import com.codecool.firstaidcompetition.repository.StationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class ExerciseControllerIntegrationTest {

    private static final String contentType = MediaType.APPLICATION_JSON_UTF8_VALUE;

    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private MockMvc mockMvc;

    private Station station;
    private Exercise exercise1;
    private Exercise exercise2;
    private Exercise exercise3;

    @Before
    public void setup() {
        stationRepository.deleteAll();
        exerciseRepository.deleteAll();

        updateDb();
    }

    private void updateDb() {
        station = new Station("Station 1", 1, "Description", null);

        exercise1 = new Exercise("first exercise", "first desc", station);
        exercise2 = new Exercise("second exercise", "second desc", station);
        exercise3 = new Exercise("third exercise", "third desc", station);
        List<Exercise> exercises = Arrays.asList(exercise1, exercise2, exercise3);
        station.setExercise(exercises);

        stationRepository.save(station);
        exerciseRepository.save(exercise1);
        exerciseRepository.save(exercise2);
        exerciseRepository.save(exercise3);
    }

    @Test
    public void putStation_WhenUpdateExisting_ThenReturn2xx() throws Exception {
        Station newStation = new Station("newStation", 2, "new station's description", null);
        stationRepository.save(newStation);

        exercise1.setDescription("new desc");
        exercise1.setName("updated name");
        exercise1.setStation(newStation);

        String exerciseJSON = parseJSON(exercise1);

        ResultActions perform = mockMvc.perform(put("/exercise/" + exercise1.getId())
                .contentType(contentType).content(exerciseJSON));
        perform
                .andExpect(status().is2xxSuccessful());

        Exercise updatedExercise = exerciseRepository.findOne(exercise1.getId());
        assertEquals(exercise1.getId(), updatedExercise.getId());
        assertEquals(exercise1.getName(), updatedExercise.getName());
        assertEquals(exercise1.getDescription(), updatedExercise.getDescription());

        assertEquals(newStation.getId(), updatedExercise.getStation().getId());
        assertEquals(newStation.getName(), updatedExercise.getStation().getName());
        assertEquals(null, updatedExercise.getStation().getCompetition());
    }

    @Test
    public void putExercise_WhenBodyIsEmpty_ThenReturn4xxError() throws Exception {
        ResultActions perform = mockMvc.perform(put("/station/" + exercise2.getId()));
        perform
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void putExercise_WhenUpdateNonExisting_ThenReturn4xxError() throws Exception {
        Exercise newExercise = new Exercise("new", "new desc", station);
        String exerciseJson = parseJSON(newExercise);

        ResultActions perform = mockMvc.perform(put("/exercise/" + 999)
                .contentType(contentType).content(exerciseJson));
        perform
                .andExpect(status().is4xxClientError())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteExercise_WhenDeleteExisting_ThenReturnWith2xx() throws Exception {
        ResultActions perform = mockMvc.perform(delete("/exercise/" + exercise3.getId()));
        perform
                .andExpect(status().isOk())
                .andExpect(status().is2xxSuccessful());

        long size = exerciseRepository.findAll().spliterator().getExactSizeIfKnown();
        assertEquals(2, size);
        assertNull(exerciseRepository.findOne(exercise3.getId()));
    }

    @Test
    public void deleteExercise_WhenDeleteNonExisting_ThenReturnWith4xxError() throws Exception {
        ResultActions perform = mockMvc.perform(delete("/exercise/" + 999));
        perform
                .andExpect(status().is4xxClientError())
                .andExpect(status().isNotFound());
    }

    @Test
    public void postExercise_WhenSaveExercise_ThenReturnWith2xx() throws Exception {
        Exercise newExercise = new Exercise("new", "new desc", station);
        String exerciseJson = parseJSON(newExercise);

        ResultActions perform = mockMvc.perform(post("/exercise/")
                .contentType(contentType).content(exerciseJson));
        perform
                .andExpect(status().is2xxSuccessful());

        long expectedId = exercise3.getId() + 1;
        assertEquals(newExercise.getName(), exerciseRepository.findOne(expectedId).getName());
        assertEquals(newExercise.getDescription(), exerciseRepository.findOne(expectedId).getDescription());
        assertEquals(station.getId(), exerciseRepository.findOne(expectedId).getStation().getId());
    }

    @Test
    public void postExercise_WhenSaveNull_ThenReturnWith4xx() throws Exception {
        ResultActions perform = mockMvc.perform(post("/exercise/"));
        perform
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void getExercise_WhenGetNonExistingId_ThenReturn4xxError() throws Exception {
        ResultActions perform = mockMvc.perform(get("/exercise/" + 999));
        perform
                .andExpect(status().is4xxClientError())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getExercises_WhenGetAll_ThenReturnJsonObject() throws Exception {
        ResultActions perform = mockMvc.perform(get("/exercise/"));
        perform
                .andExpect(content().contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(exercise1.getName())))
                .andExpect(jsonPath("$[0].description", is(exercise1.getDescription())))

                .andExpect(jsonPath("$[1].name", is(exercise2.getName())))
                .andExpect(jsonPath("$[1].description", is(exercise2.getDescription())))
                .andExpect(jsonPath("[2].name", is(exercise3.getName())))
                .andExpect(jsonPath("[2].description", is(exercise3.getDescription())));
    }

    @Test
    public void getExercises_WhenGetAll_ThenReturnJsonObjectWithValidDependencies() throws Exception {
        ResultActions perform = mockMvc.perform(get("/exercise/"));
        perform
                .andExpect(jsonPath("$[0].station.id", is((int) station.getId())))
                .andExpect(jsonPath("$[1].station.id", is((int) station.getId())))
                .andExpect(jsonPath("$[2].station.id", is((int) station.getId())));
    }

    @Test
    public void getExercise_WhenGetById_ThenReturnJsonObject() throws Exception {
        ResultActions perform = mockMvc.perform(get("/exercise/" + exercise1.getId()));
        perform
                .andExpect(content().contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(exercise1.getName())))
                .andExpect(jsonPath("$.description", is(exercise1.getDescription())))
                .andExpect(jsonPath("$.station.id", is((int) station.getId())))
                .andExpect(jsonPath("$.station.description", is(exercise1.getStation().getDescription())));
    }

    private String parseJSON(Exercise exercise) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(exercise);
    }

}
