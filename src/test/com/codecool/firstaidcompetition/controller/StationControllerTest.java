package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.Application;
import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.service.StationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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

    @MockBean
    private StationService stationService;

    private Station station1;
    private Station station2;
    private Station station3;
    private Competition competition;

    @Before
    public void setup() {
        competition = new Competition("first race",
                "Egerszeg", "2015-05-25", null);
        competition.setId(1);
        station1 = new Station("first", 1, "first desc", competition);
        station2 = new Station("second", 2, "second desc", competition);
        station3 = new Station("third", 3, "third desc", competition);
        station1.setId(1L); station2.setId(2L); station3.setId(3L);
        List<Station> stations = Arrays.asList(station1, station2 ,station3);

        given(stationService.findAll()).willReturn(stations);
        given(stationService.findById(station2.getId())).willReturn(station2);
    }

    @Test
    public void givenStation_WhenGetById_ThenReturnJsonObject() throws Exception {
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
    public void givenStations_WhenGetAll_ThenReturnJsonArray() throws Exception {
        ResultActions perform = mockMvc.perform(get("/station/"));
        perform
            .andExpect(content().contentType(contentType))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].name", is(station1.getName())))
            .andExpect(jsonPath("$[1].number", is(station2.getNumber())))
            .andExpect(jsonPath("$[2].description", is(station3.getDescription())))

            .andExpect(jsonPath("$[0].competition.name", is(station1.getCompetition().getName()) ))
            .andExpect(jsonPath("$[1].competition.location", is(station2.getCompetition().getLocation()) ))
            .andExpect(jsonPath("$[2].competition.id", is( (int) station3.getCompetition().getId()) ));
    }


}
