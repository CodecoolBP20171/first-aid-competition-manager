package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.database.DBHandler;
import com.codecool.firstaidcompetition.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
public class HTTPController {

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
        // get example data
        Iterable<Competition> competitionList = dbHandler.getAllCompetition();
        competitionList.forEach(comp -> System.out.println(comp.getName()));
        return "index";
    }

    @RequestMapping(value = {"/competition"}, method = RequestMethod.GET)
    public String getCompetitions(Model model){
        Iterable<Competition> competitionList = dbHandler.getAllCompetition();
//        competitionList.forEach(comp -> System.out.println(comp.getName()));

        model.addAttribute("listOfCompetitions", competitionList);
        return "competition_table";
    }


    @GetMapping(value = "competition/add")
    public String addCompetition(Model model){
        model.addAttribute("competition", new Competition());
        return "competition_add";
    }

    @PostMapping(value = "competition/add")
    public String submitCompetition(@ModelAttribute Competition competition){
        dbHandler.getCompetitionRepository().save(competition);
//        System.out.println(competition.getName());
        return "result";
    }


    public void updateTable(){
        try {
            dbHandler.populateDB();
        } catch (ParseException e){
            e.printStackTrace();
        }
    }
}
