package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.service.StationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(StationController.class)
@WithMockUser
public class StationControllerTest {

    private static final String contentType = MediaType.APPLICATION_JSON_UTF8_VALUE;

    @Autowired
    private MockMvc mockMvc;
    private HttpMessageConverter jsonConverter;

    @MockBean
    private StationService stationService;

    private Station station1;
    private Station station2;
    private Station station3;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        jsonConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null", jsonConverter);
    }

    @Before
    public void setup() {
        Competition competition = new Competition("first race",
                "Egerszeg", "2015-05-25", null);
        competition.setId(1);
        station1 = new Station("first", 1, "first desc", competition);
        station2 = new Station("second", 2, "second desc", competition);
        station3 = new Station("third", 3, "third desc", competition);
        station1.setId(1L);
        station2.setId(2L);
        station3.setId(3L);
        List<Station> stations = Arrays.asList(station1, station2, station3);

        given(stationService.findAll()).willReturn(stations);
        given(stationService.findById(station2.getId())).willReturn(station2);
    }

    @Test
    public void putStation_WhenUpdateWithExisting_ThenReturn2xx() throws Exception {
        Station newStat = new Station("new station", 44, "desc", null);
        String stationJson = json(newStat);
        ResultActions perform = mockMvc.perform(put("/station/" + 2)
            .contentType(contentType).content(stationJson));

        perform
            .andExpect(status().isOk())
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void putStation_WhenUpdateWithNull_ThenReturn4xxError() throws Exception {
        ResultActions perform = mockMvc.perform(put("/station/" + 999));
        perform
            .andExpect(status().is4xxClientError());
    }

    @Test
    public void putStation_WhenUpdateNonExisting_ThenReturn4xxError() throws Exception {
        Station newStat = new Station("new station", 44, "desc", null);
        String stationJson = json(newStat);
        ResultActions perform = mockMvc.perform(put("/station/" + 999)
            .contentType(contentType).content(stationJson));

        perform
            .andExpect(status().is4xxClientError())
            .andExpect(status().isNotFound());
    }

    @Test
    public void deleteStation_WhenDeleteExisting_ThenReturnWith2xx() throws Exception {
        ResultActions perform = mockMvc.perform(delete("/station/" + 2));
        perform
            .andExpect(status().isOk())
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void deleteStation_WhenDeleteNotExisting_ThenReturnWith4xxError() throws Exception {
        ResultActions perform = mockMvc.perform(delete("/station/" + 999));
        perform
            .andExpect(status().is4xxClientError())
            .andExpect(status().isNotFound());
    }

    @Test
    public void postStation_WhenSaveStation_ThenReturnWith2xx() throws Exception {
        Station newStat = new Station("new station", 44, "desc", null);
        String stationJson = json(newStat);
        ResultActions perform = mockMvc.perform(post("/station/")
                .contentType(contentType).content(stationJson));

        perform
            .andExpect(status().is2xxSuccessful());
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

    private String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        jsonConverter.write(
                o, MediaType.APPLICATION_JSON_UTF8, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
