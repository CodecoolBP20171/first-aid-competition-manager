package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.model.User;
import com.codecool.firstaidcompetition.repository.CompetitionRepository;
import com.codecool.firstaidcompetition.repository.DBHandler;
import com.codecool.firstaidcompetition.repository.StationRepository;
import com.codecool.firstaidcompetition.repository.UserRepository;
import com.codecool.firstaidcompetition.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

@Controller
public class HTTPController {

    private static final Logger logger = LoggerFactory.getLogger(HTTPController.class.getName());

    @Autowired
    private DBHandler dbHandler;
    private boolean isDBUpdated = false;

    @RequestMapping(value = {"/", "/index"})
    public String indexPage() {
        if (!isDBUpdated) {
            updateTable();
            isDBUpdated = true;
        }
        return "index";
    }

    private void updateTable() {
        try {
            dbHandler.populateDB();
            logger.info("Table updated with dummy data");
        } catch (ParseException e) {
            e.printStackTrace();
            logger.info("Can't update the table with dummy data");
        }
    }
}
