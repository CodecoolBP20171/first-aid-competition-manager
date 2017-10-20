package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.exception.CompetitionNotFoundException;
import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public Competition getCompetitionByIdRest(@PathVariable("id") long id) throws CompetitionNotFoundException {
        isValidCompetitonId(id);
        return competitionService.getCompetitionByIdService(id);
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public ResponseEntity<String> addCompetitionRest(@RequestBody Competition competition) {
        competitionService.addNewCompetitionService(competition);
        return new ResponseEntity<String>("Created new competition!", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<String> editCompetitionRest(@PathVariable("id") long id, @RequestBody Competition competition) throws CompetitionNotFoundException {
        isValidCompetitonId(id);
        competitionService.editCompetitionService(id, competition);
        return new ResponseEntity<String>("Competition updated!", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCompetitionRest(@PathVariable("id") long id) throws CompetitionNotFoundException {
        isValidCompetitonId(id);
        competitionService.deleteCompetitionService(id);
        return new ResponseEntity<String>("Competition deleted!", HttpStatus.OK);
    }

    @ExceptionHandler(CompetitionNotFoundException.class)
    public void handleCompetitionNotFound(CompetitionNotFoundException exception,
                                          HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    private void isValidCompetitonId(long id) throws CompetitionNotFoundException {
        Competition competition = competitionService.getCompetitionByIdService(id);
        if (competition == null) {
            throw new CompetitionNotFoundException("Competition with id: " + id + " not found!");
        }
    }
}
