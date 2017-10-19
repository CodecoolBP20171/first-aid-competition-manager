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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
//@WebMvcTest(StationController.class)
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class StationControllerTest {

    private static final String contentType = MediaType.APPLICATION_JSON_UTF8_VALUE;

    private MockMvc mockMvc;

    @MockBean
    private StationService stationService;

    @Before
    public void setup() throws Exception {
        Competition competition = new Competition("first race",
                "Egerszeg", "2015-05-25", null);
        Station station1 = new Station("first", 1, "first desc", competition);
        Station station2 = new Station("second", 2, "second desc", competition);
        Station station3 = new Station("third", 3, "third desc", competition);
        List<Station> stations = Arrays.asList(station1, station2 ,station3);

        given(stationService.findAll()).willReturn(stations);
    }

    @Test
    public void getAll_WhenInvoke_GetAllStations() {
        stationService.findAll().forEach(s -> System.out.println(s.getName()));
    }

}
