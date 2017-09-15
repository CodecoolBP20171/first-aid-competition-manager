package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.repository.CompetitionRepository;
import com.codecool.firstaidcompetition.repository.UserRepository;
import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger logger = LoggerFactory.getLogger(HTTPController.class.getName());

    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
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

    @RequestMapping(value = {"/delete/{competitionID}"}, method = RequestMethod.GET)
    public String deleteCompetition(Model model, @PathVariable("competitionID") int itemid){
        Competition competition = competitionRepository.findOne(Long.valueOf(itemid));
        competitionRepository.delete(competition);
        Iterable<Competition> competitionList = competitionRepository.findAll();
        model.addAttribute("listOfCompetitions", competitionList);
        model.addAttribute("competition", new Competition());
        logger.info("Deleted competition with id : " + itemid);
        return "competitions/competition_table";
    }


    @RequestMapping(value={"/edit"},method = RequestMethod.POST)
    public ModelAndView editCompetition(@ModelAttribute Competition competition){
        Competition competitionEdit = competitionRepository.findOne(Long.valueOf(competition.getId()));
        competitionEdit.setName(competition.getName());
        competitionEdit.setLocation(competition.getLocation());
        //Convert date to string
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = competition.getDateOfEvent();
        String compDate = df.format(date);

        competitionEdit.setDateOfEvent(compDate);
        competitionRepository.save(competitionEdit);
        logger.info("Edited competition with id : " + competitionEdit.getId());
        return new ModelAndView("redirect:/competition/");
    }

    @PostMapping(value = "/add")
    public ModelAndView submitCompetition(@ModelAttribute Competition competition){
        // Query a user from the db (owner has to be redirect from the session)
        User dummyUser = userRepository.findOne(1L);
        competition.setOwner(dummyUser);

        competitionRepository.save(competition);
        logger.info("Save competition to the db, " +
                        "[name: {}; location: {}; date: {}, owner: {}]",
                competition.getName(), competition.getLocation(), competition.getDateOfEvent(),
                competition.getOwner());
        return new ModelAndView("redirect:/competition/");
    }
}
