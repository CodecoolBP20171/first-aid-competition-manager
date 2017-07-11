package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.database.DBHandler;
import com.codecool.firstaidcompetition.model.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import org.slf4j.Logger;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HTTPController {
    private static final Logger logger = LoggerFactory.getLogger(HTTPController.class.getName());
    private DBHandler dbHandler;

    @Autowired
    public HTTPController(DBHandler dbHandler){
        this.dbHandler = dbHandler;
        updateTable();
    }

    @RequestMapping("/registration")
    public String registrationPage(){
        return "registration_form";
    }

    @RequestMapping("/index")
    public String indexPage(){
        return "index";
    }

    @RequestMapping(value = {"/competition"}, method = RequestMethod.GET)
    public String getCompetitions(Model model){
        Iterable<Competition> competitionList = dbHandler.getAllCompetition();
        model.addAttribute("listOfCompetitions", competitionList);

        logger.info("Mappinng the competition route");
        return "competition_table";
    }


    @GetMapping(value = "competition/add")
    public String addCompetition(Model model){
        model.addAttribute("competition", new Competition());
        return "competition_add";
    }

    @PostMapping(value = "competition/add")
    public ModelAndView submitCompetition(@ModelAttribute Competition competition){
        // Query a user from the db (owner has to be redirect from the session)
        User dummyUser = dbHandler.getUserRepository().findOne(1L);
        competition.setOwner(dummyUser);

        dbHandler.getCompetitionRepository().save(competition);
        logger.info("Save competition to the db, " +
                "[name: {}; location: {}; date: {}, owner: {}]",
                competition.getName(), competition.getLocation(), competition.getDateOfEvent(),
                competition.getOwner());
        return new ModelAndView("redirect:/competition");
    }


    public void updateTable(){
        try {
            dbHandler.populateDB();
            logger.info("Table updated with dummy data");
        } catch (ParseException e){
            e.printStackTrace();
            logger.info("Can't update the table with dummy data");
        }
    }
}
