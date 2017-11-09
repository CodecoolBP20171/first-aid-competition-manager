package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.Application;
import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.repository.CompetitionRepository;
import com.codecool.firstaidcompetition.repository.StationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
        classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class StationControllerIntegrationTest {

    private static final String contentType = MediaType.APPLICATION_JSON_UTF8_VALUE;

    private Station station1;
    private Station station2;
    private Station station3;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private CompetitionRepository competitionRepository;

    @Before
    public void setup() {
        competitionRepository.deleteAll();
        stationRepository.deleteAll();

        updateDB();
    }

    private void updateDB() {
        Competition competition = new Competition("first race",
                "Egerszeg", "2015-05-25", null);
        competitionRepository.save(competition);

        station1 = new Station("first", 1, "first desc", competition);
        station2 = new Station("second", 2, "second desc", competition);
        station3 = new Station("third", 3, "third desc", competition);

        stationRepository.save(station1);
        stationRepository.save(station2);
        stationRepository.save(station3);
    }

    @Test
    public void putStation_WhenUpdateExisting_ThenReturn2xx() throws Exception {
        Competition newComp = new Competition("new", "location", "2015-07-08", null);
        competitionRepository.save(newComp);

        station1.setDescription("new desc");
        station1.setNumber(222);
        station1.setCompetition(newComp);

        String stationJson = parseStationWithDate(station1);

        ResultActions perform = mockMvc.perform(put("/station/" + station1.getId())
                .contentType(contentType).content(stationJson));
        perform
                .andExpect(status().is2xxSuccessful());

        Station updatedStat = stationRepository.findOne(station1.getId());
        assertEquals(station1.getId(), updatedStat.getId());
        assertEquals(station1.getNumber(), updatedStat.getNumber());
        assertEquals(station1.getName(), updatedStat.getName());
        assertEquals(station1.getDescription(), updatedStat.getDescription());

        assertEquals(station1.getCompetition().getId(), updatedStat.getCompetition().getId());
        assertEquals(station1.getCompetition().getName(), updatedStat.getCompetition().getName());
        assertEquals(station1.getCompetition().getLocation(), updatedStat.getCompetition().getLocation());
    }

    @Test
    public void putStation_WhenUpdateWithNull_ThenReturn4xxError() throws Exception {
        ResultActions perform = mockMvc.perform(put("/station/" + station3.getId()));
        perform
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void putStation_WhenGetWrongId_ThenReturn4xxError() throws Exception {
        ResultActions perform = mockMvc.perform(put("/station/" + 999));
        perform
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void putStation_WhenUpdateNonExisting_ThenReturn4xxError() throws Exception {
        Station newStat = new Station("new station", 44, "desc", null);
        String stationJson = parseStationWithDate(newStat);

        ResultActions perform = mockMvc.perform(put("/station/" + 999)
                .contentType(contentType).content(stationJson));
        perform
                .andExpect(status().is4xxClientError())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteStation_WhenDeleteExisting_ThenReturnWith2xx() throws Exception {
        ResultActions perform = mockMvc.perform(delete("/station/" + station1.getId()));
        perform
                .andExpect(status().isOk())
                .andExpect(status().is2xxSuccessful());

        long size = stationRepository.findAll().spliterator().getExactSizeIfKnown();
        assertEquals(2, size);
        assertNull(stationRepository.findOne(station1.getId()));
    }

    @Test
    public void deleteStation_WhenDeleteNonExisting_ThenReturnWith4xxError() throws Exception {
        ResultActions perform = mockMvc.perform(delete("/station/" + 999));
        perform
                .andExpect(status().is4xxClientError())
                .andExpect(status().isNotFound());
    }

    @Test
    public void postStation_WhenSaveStation_ThenReturnWith2xx() throws Exception {
        Station newStat = new Station("new station", 44, "desc", null);
        String stationJson = parseStationWithDate(newStat);

        ResultActions perform = mockMvc.perform(post("/station/")
                .contentType(contentType).content(stationJson));
        perform
                .andExpect(status().is2xxSuccessful());

        long expectedId = station3.getId() + 1;
        assertEquals(newStat.getName(), stationRepository.findOne(expectedId).getName());
        assertEquals(newStat.getNumber(), stationRepository.findOne(expectedId).getNumber());
        assertEquals(null, stationRepository.findOne(expectedId).getCompetition());
    }

    @Test
    public void postStation_WhenSaveNull_ThenReturnWith4xx() throws Exception {
        ResultActions perform = mockMvc.perform(post("/station/"));
        perform
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getStation_WhenGetNonExistingId_ThenReturn4xxError() throws Exception {
        ResultActions perform = mockMvc.perform(get("/station/" + 999));
        perform
                .andExpect(status().is4xxClientError())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getStation_WhenGetById_ThenReturnJsonObject() throws Exception {
        ResultActions perform = mockMvc.perform(get("/station/" + station2.getId()));
        perform
                .andExpect(content().contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(station2.getName())))
                .andExpect(jsonPath("$.number", is(station2.getNumber())))
                .andExpect(jsonPath("$.description", is(station2.getDescription())))
                .andExpect(jsonPath("$.competition.id", is((int) station2.getCompetition().getId())));
    }

    @Test
    public void getStations_WhenGetAll_ThenReturnJsonArray() throws Exception {
        ResultActions perform = mockMvc.perform(get("/station/"));
        perform
                .andExpect(content().contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(station1.getName())))
                .andExpect(jsonPath("$[1].number", is(station2.getNumber())))
                .andExpect(jsonPath("$[2].description", is(station3.getDescription())))

                .andExpect(jsonPath("$[0].competition.name", is(station1.getCompetition().getName())))
                .andExpect(jsonPath("$[1].competition.location", is(station2.getCompetition().getLocation())))
                .andExpect(jsonPath("$[2].competition.id", is((int) station3.getCompetition().getId())));
    }

    // Another (much easier) JSON parser, parse Date object properly to Json
    private String parseStationWithDate(Station station) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        mapper.setDateFormat(df);
        return mapper.writeValueAsString(station);
    }

}
