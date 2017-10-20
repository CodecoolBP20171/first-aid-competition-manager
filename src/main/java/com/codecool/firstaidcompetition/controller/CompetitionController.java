package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/competition")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    //***** REST services *****//
    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public Iterable<Competition> getCompetitionsRest() {
        return competitionService.getAllCompetitionsService();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Competition getCompetitionByIdRest(@PathVariable("id") long id) {
        return competitionService.getCompetitionByIdService(id);
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public void addCompetitionRest(@RequestBody Competition competition) {
        competitionService.addNewCompetitionService(competition);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public void editCompetitionRest(@PathVariable("id") long id, @RequestBody Competition competition) {
        competitionService.editCompetitionService(id, competition);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCompetitionRest(@PathVariable("id") long id) {
        competitionService.deleteCompetitionService(id);
    }
}
