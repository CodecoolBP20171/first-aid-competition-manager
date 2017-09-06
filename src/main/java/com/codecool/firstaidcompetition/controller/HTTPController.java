package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.repository.DBHandler;
import com.codecool.firstaidcompetition.model.*;
import com.codecool.firstaidcompetition.repository.StationRepository;
import com.codecool.firstaidcompetition.repository.UserRepository;
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

    @Autowired
    private DBHandler dbHandler;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StationRepository stationRepository;

    private boolean isDBUpdated = false;

    @RequestMapping(value = {"/", "/index"})
    public String indexPage(){
        if (!isDBUpdated){
            updateTable();
            isDBUpdated = true;
        }
        return "index";
    }

    @GetMapping("/registration")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "registration_form";
    }

    @PostMapping("/registration")
    public ModelAndView submitUser(@ModelAttribute User user){
        userRepository.save(user);

        logger.info("Save USer to the db, " +
                        "[fullName: {}; userName: {}; email: {}, password: {}]",
                user.getFullName(), user.getUserName(), user.getEmail(),
                user.getPassword());
        return new ModelAndView("redirect:/index");
    }


    @RequestMapping(value = {"/station"}, method = RequestMethod.GET)
    public String getStations(Model model){
        Iterable<Station> stationList = stationRepository.findAll();
        model.addAttribute("listOfStations", stationList);

        logger.info("Mappinng the station route");
        return "station_table";
    }

    @GetMapping(value = "station/add")
    public String addStation(Model model){
        model.addAttribute("station", new Station());
        return "station_form";
    }

    @PostMapping(value = "station/add")
    public ModelAndView submitStation(@ModelAttribute Station station){
        // Query a user from the db (owner has to be redirect from the session)
        stationRepository.save(station);
        logger.info("Save station to the db, " +
                        "[name: {}; location: {}; date: {}, owner: {}]",
                station.getName(), station.getNumber(), station.getDescription(),
                station.getCompetition());
        return new ModelAndView("redirect:/station");
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
