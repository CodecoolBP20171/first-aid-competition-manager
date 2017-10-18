package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;

@Controller
@RequestMapping("/competition")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @RequestMapping(value = {"/", ""})
    public String getCompetitions(Model model) {
        model.addAttribute("competition", new Competition());
        model.addAttribute("listOfCompetitions", competitionService.getAllCompetitionsService());
        return "competitions/competition_table";
    }

    @GetMapping(value = "/add")
    public String addCompetition(Model model) {
        model.addAttribute("competition", new Competition());
        return "competitions/competition_form";
    }

    @RequestMapping(value = "/delete/{competitionID}")
    public ModelAndView deleteCompetition(@PathVariable("competitionID") long itemid) {
        competitionService.deleteCompetitionService(itemid);
        return new ModelAndView("redirect:/competition/");
    }

    @PostMapping(value = "/edit")
    public ModelAndView editCompetition(@ModelAttribute Competition competition) {
        competitionService.editCompetitionService(competition);
        return new ModelAndView("redirect:/competition/");
    }

    @PostMapping(value = "/add")
    public ModelAndView submitCompetition(@ModelAttribute Competition competition) {
        competitionService.addNewCompetitionService(competition);
        return new ModelAndView("redirect:/competition/");
    }

    //***** REST services *****//
    @ResponseBody
    @RequestMapping(value = "/rest", method = RequestMethod.GET)
    public Iterable<Competition> getCompetitionsRest() {
        return competitionService.getAllCompetitionsService();
    }

    @ResponseBody
    @RequestMapping(value = "/rest/{id}", method = RequestMethod.GET)
    public Competition getCompetitionByIdRest(@PathVariable("id") long id) {
        return competitionService.getCompetitionByIdService(id);
    }

    @ResponseBody
    @RequestMapping(value = "/rest/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCompetitionRest(@RequestBody Competition competition) {
        competitionService.addNewCompetitionService(competition);
    }

    @ResponseBody
    @RequestMapping(value = "/rest/{id}", method = RequestMethod.DELETE)
    public void deleteCompetitionRest(@PathVariable("id") long id) {
        competitionService.deleteCompetitionService(id);
    }
}
