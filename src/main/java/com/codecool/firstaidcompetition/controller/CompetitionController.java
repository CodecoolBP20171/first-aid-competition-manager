package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.repository.CompetitionRepository;
import com.codecool.firstaidcompetition.repository.UserRepository;
import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.User;
import com.codecool.firstaidcompetition.service.UserDetailsImpl;
import com.codecool.firstaidcompetition.service.UserService;
import com.codecool.firstaidcompetition.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/competition")
public class CompetitionController {

    private static final Logger logger = LoggerFactory.getLogger(CompetitionController.class);

    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = {"/", ""})
    public String getCompetitions(Model model){
        Iterable<Competition> competitionList = competitionRepository.findAll();
        model.addAttribute("competition", new Competition());
        model.addAttribute("listOfCompetitions", competitionList);
        return "competitions/competition_table";
    }

    @GetMapping(value = "/add")
    public String addCompetition(Model model){
        model.addAttribute("competition", new Competition());
        return "competitions/competition_form";
    }

    @RequestMapping(value = "/delete/{competitionID}")
    public ModelAndView deleteCompetition(@PathVariable("competitionID") int itemid){
        Competition competition = competitionRepository.findOne((long) itemid);
        competitionRepository.delete(competition);
        logger.info("Deleted competition with id: " + itemid);

        return new ModelAndView("redirect:/competition/");
    }


    @PostMapping(value="/edit")
    public ModelAndView editCompetition(@ModelAttribute Competition competition){
        Competition competitionEdit = competitionRepository.findOne(competition.getId());
        competitionEdit.setName(competition.getName());
        competitionEdit.setLocation(competition.getLocation());
        //Convert date to string
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = competition.getDateOfEvent();
        String compDate = df.format(date);

        competitionEdit.setDateOfEvent(compDate);
        competitionRepository.save(competitionEdit);
        logger.info("Edited competition with id: " + competitionEdit.getId());
        return new ModelAndView("redirect:/competition/");
    }

    @PostMapping(value = "/add")
    public ModelAndView submitCompetition(@ModelAttribute Competition competition){
        User authenticatedUser = userService.getAuthenticatedUser();
        competition.setOwner(authenticatedUser);
        competitionRepository.save(competition);

        logger.info("Save competition to the db, " +
                        "[name: {}; location: {}; date: {}, owner: {}]",
                competition.getName(), competition.getLocation(), competition.getDateOfEvent(),
                competition.getOwner());
        return new ModelAndView("redirect:/competition/");
    }
}
