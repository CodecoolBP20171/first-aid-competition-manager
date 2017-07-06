package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.database.CompetitionRepository;
import com.codecool.firstaidcompetition.database.DBHandler;
import com.codecool.firstaidcompetition.model.*;
import org.hibernate.validator.internal.engine.messageinterpolation.InterpolationTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class HTTPController {

    private DBHandler dbHandler;

    @Autowired
    public HTTPController(DBHandler dbHandler){
        this.dbHandler = dbHandler;
    }

    @RequestMapping("/registration")
    public String registrationPage(){
        return "registration_form";
    }

    @RequestMapping("/index")
    public String indexPage(){
        updateTable();

        // get example data
        Iterable<Competition> competitionList = dbHandler.findAll();
        competitionList.forEach(comp -> System.out.println(comp.getName()));
        return "index";
    }

    @RequestMapping(value = {"/competitions"}, method = RequestMethod.GET)
    public String getCompetitions(Model model){
        Iterable<Competition> competitionList = dbHandler.findAll();
        competitionList.forEach(comp -> System.out.println(comp.getName()));

        model.addAttribute("listOfCompetitions", competitionList);
        return "competition_table";
    }

    public void updateTable(){
        try {
            dbHandler.populateDB();
        } catch (ParseException e){
            e.printStackTrace();
        }
    }
}
