package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping(value = "/rest", method = RequestMethod.GET, produces = "application/json")
    public Iterable<Competition> getCompetitionsRest() {
        return competitionService.getAllCompetitionsService();
    }
}
