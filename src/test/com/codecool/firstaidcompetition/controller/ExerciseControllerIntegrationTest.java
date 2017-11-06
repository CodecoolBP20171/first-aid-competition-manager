package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.Application;
import com.codecool.firstaidcompetition.model.Exercise;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.repository.ExerciseRepository;
import com.codecool.firstaidcompetition.repository.StationRepository;
import com.codecool.firstaidcompetition.service.ExerciseService;
import com.codecool.firstaidcompetition.service.StationService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
        exercise1 = new Exercise("second exercise", "second desc", station);
        exercise1 = new Exercise("third exercise", "third desc", station);
    }

    

}
