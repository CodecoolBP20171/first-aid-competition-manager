package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.database.CompetitionRepository;
import com.codecool.firstaidcompetition.database.UserRepository;
import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CompetitionController {

    private static final Logger logger = LoggerFactory.getLogger(HTTPController.class.getName());

    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/competition", method = RequestMethod.GET)
    public String getCompetitions(Model model){
        Iterable<Competition> competitionList = competitionRepository.findAll();
        model.addAttribute("listOfCompetitions", competitionList);
        return "competition_table";
    }

    @GetMapping(value = "competition/add")
    public String addCompetition(Model model){
        model.addAttribute("competition", new Competition());
        return "competition_form";
    }

    @PostMapping(value = "competition/add")
    public ModelAndView submitCompetition(@ModelAttribute Competition competition){
        // Query a user from the db (owner has to be redirect from the session)
        User dummyUser = userRepository.findOne(1L);
        competition.setOwner(dummyUser);

        competitionRepository.save(competition);
        logger.info("Save competition to the db, " +
                        "[name: {}; location: {}; date: {}, owner: {}]",
                competition.getName(), competition.getLocation(), competition.getDateOfEvent(),
                competition.getOwner());
        return new ModelAndView("redirect:/competition");
    }
}
