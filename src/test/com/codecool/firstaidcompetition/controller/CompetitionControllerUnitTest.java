package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.service.CompetitionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CompetitionController.class)
@WithMockUser
public class CompetitionControllerUnitTest {

    private static final String contentType = MediaType.APPLICATION_JSON_UTF8_VALUE;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompetitionService competitionService;

    private Competition competition1;
    private Competition competition2;
    private Competition competition3;

    @Before
    public void setup() {
        competition1 = new Competition("First competition", "Budapest", "2015-05-25", null);
        competition2 = new Competition("Second competition", "Miskolc", "2016-06-26", null);
        competition3 = new Competition("Third competition", "Eger", "2017-07-27", null);
        competition1.setId(1L);
        competition2.setId(2L);
        competition3.setId(3L);

        List<Competition> competitions = Arrays.asList(competition1, competition2, competition3);

        given(competitionService.getAllCompetitionsService()).willReturn(competitions);
        given(competitionService.getCompetitionByIdService(competition2.getId())).willReturn(competition2);
    }

    @Test
    public void putCompetition_WhenUpdateWithExisting_ThenReturn2xx() throws Exception {
        Competition newCompetition = new Competition("New competition", "Győr", "2018-07-27", null);
        String competitionJson = parseCompetitionWithDate(newCompetition);
        ResultActions perform = mockMvc.perform(put("/competition/" + 2)
                .contentType(contentType).content(competitionJson));

        perform
                .andExpect(status().isOk())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void putCompetition_WhenUpdateWithNull_ThenReturn4xxError() throws Exception {
        ResultActions perform = mockMvc.perform(put("/competition/" + 3));
        perform
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void putCompetition_WhenGetWrongId_ThenReturn4xxError() throws Exception {
        ResultActions perform = mockMvc.perform(put("/competition/" + 999));
        perform
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void putCompetition_WhenUpdateNonExisting_ThenReturn4xxError() throws Exception {
        Competition newCompetition = new Competition("New competition", "Győr", "2018-07-27", null);
        String competitionJson = parseCompetitionWithDate(newCompetition);
        ResultActions perform = mockMvc.perform(put("/competition/" + 999)
                .contentType(contentType).content(competitionJson));

        perform
                .andExpect(status().is4xxClientError())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteCompetition_WhenDeleteExisting_ThenReturnWith2xx() throws Exception {
        ResultActions perform = mockMvc.perform(delete("/competition/" + 2));
        perform
                .andExpect(status().isOk())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void deleteCompetition_WhenDeleteNonExisting_ThenReturnWith4xxError() throws Exception {
        ResultActions perform = mockMvc.perform(delete("/competition/" + 999));
        perform
                .andExpect(status().is4xxClientError())
                .andExpect(status().isNotFound());
    }

    @Test
    public void postCompetition_WhenSaveCompetition_ThenReturnWith2xx() throws Exception {
        Competition newCompetition = new Competition("New competition", "Győr", "2018-07-27", null);
        String competitionJson = parseCompetitionWithDate(newCompetition);
        ResultActions perform = mockMvc.perform(post("/competition/")
                .contentType(contentType).content(competitionJson));

        perform
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void postCompetition_WhenSaveNull_ThenReturnWith4xx() throws Exception {
        ResultActions perform = mockMvc.perform(post("/competition/"));
        perform
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getCompetition_WhenGetNonExistingId_ThenReturn4xxError() throws Exception {
        ResultActions perform = mockMvc.perform(get("/competition/" + 999));
        perform
                .andExpect(status().is4xxClientError())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getCompetition_WhenGetById_ThenReturnJsonObject() throws Exception {
        ResultActions perform = mockMvc.perform(get("/competition/" + competition2.getId()));

        perform
                .andExpect(content().contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(competition2.getName())))
                .andExpect(jsonPath("$.location", is(competition2.getLocation())))
                .andExpect(jsonPath("$.dateOfEvent", is(competition2.getDateOfEvent().getTime())));
    }

    @Test
    public void getCompetition_WhenGetAll_ThenReturnJsonArray() throws Exception {
        ResultActions perform = mockMvc.perform(get("/competition/"));
        perform
                .andExpect(content().contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(competition1.getName())))
                .andExpect(jsonPath("$[1].location", is(competition2.getLocation())))
                .andExpect(jsonPath("$[2].dateOfEvent", is(competition3.getDateOfEvent().getTime())));
    }

    // Another (much easier) JSON parser, parse Date object properly to Json
    private String parseCompetitionWithDate(Competition competition) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        mapper.setDateFormat(df);
        return mapper.writeValueAsString(competition);
    }

}
