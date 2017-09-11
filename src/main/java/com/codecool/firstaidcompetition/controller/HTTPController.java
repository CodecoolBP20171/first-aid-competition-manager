package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.model.User;
import com.codecool.firstaidcompetition.repository.CompetitionRepository;
import com.codecool.firstaidcompetition.repository.DBHandler;
import com.codecool.firstaidcompetition.repository.StationRepository;
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
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CompetitionRepository competitionRepository;

    private boolean isDBUpdated = false;

    @RequestMapping(value = {"/", "/index"})
    public String indexPage() {
        if (!isDBUpdated) {
            updateTable();
            isDBUpdated = true;
        }
        return "index";
    }

    @GetMapping("/registration")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "registration_form";
    }

    @RequestMapping("/registration/{userName}")
    @ResponseBody   // can return with anything
    public boolean checkUsernameIsExists(@PathVariable String userName) {
        return userService.checkUsernameAlreadyExists(userName);
    }

    @PostMapping("/registration")
    public ModelAndView submitUser(@ModelAttribute User user,
                                   @RequestParam("userRole") String role) {
        userService.saveUser(user, role); // save with hashing pass
        logger.info("Save USer to the db, " +
                        "[fullName: {}; userName: {}; email: {}, password: {}]",
                user.getFullName(), user.getUserName(), user.getEmail(),
                user.getPassword());
        return new ModelAndView("redirect:/index");
    }

    @RequestMapping(value = {"/station"}, method = RequestMethod.GET)
    public String getStations(Model model) {
        Iterable<Station> stationList = stationRepository.findAll();
        model.addAttribute("listOfStations", stationList);
        model.addAttribute("station", new Station());
        Iterable<Competition> competitionList = competitionRepository.findAll();
        model.addAttribute("listOfCompetitions", competitionList);
        logger.info("Mappinng the station route");
        return "station_table";
    }

    @RequestMapping(value={"/station/edit"},method = RequestMethod.POST)
    public ModelAndView editStation(@ModelAttribute Station station){
        //stationRepository.save(station);
        Station stationEdit = stationRepository.findOne(station.getId());
        stationEdit.setName(station.getName());
        stationEdit.setDescription(station.getDescription());
        System.out.println(station.getCompetition().getName());

        stationEdit.setCompetition(station.getCompetition());
        stationEdit.setNumber(station.getNumber());
        stationRepository.save(stationEdit);
        logger.info("Edited user with id : " + station.getId());
        return new ModelAndView("redirect:/station");
    }

    @RequestMapping(value = {"/station_delete/{stationID}"}, method = RequestMethod.GET)
    public String deleteStation(Model model, @PathVariable("stationID") int itemid){
        Station station = stationRepository.findOne(itemid);
        stationRepository.delete(station);
        Iterable<Station> stationList = stationRepository.findAll();
        model.addAttribute("listOfStations", stationList);
        model.addAttribute("station", new Station());
        Iterable<Competition> competitionList = competitionRepository.findAll();
        model.addAttribute("listOfCompetitions", competitionList);
        logger.info("Deleted user with id : " + itemid);
        return "station_table";
    }



    @GetMapping(value = "station/add")
    public String addStation(Model model) {
        Iterable<Competition> competitionList = competitionRepository.findAll();
        model.addAttribute("listOfCompetitions", competitionList);
        model.addAttribute("station", new Station());
        return "station_form";
    }

    @PostMapping(value = "station/add")
    public ModelAndView submitStation(@ModelAttribute Station station) {
        stationRepository.save(station);
        logger.info("Save station to the db, " +
                        "[name: {}; location: {}; date: {}, owner: {}]",
                station.getName(), station.getNumber(), station.getDescription(),
                station.getCompetition());
        return new ModelAndView("redirect:/station");
    }

    public void updateTable() {
        try {
            dbHandler.populateDB();
            logger.info("Table updated with dummy data");
        } catch (ParseException e) {
            e.printStackTrace();
            logger.info("Can't update the table with dummy data");
        }
    }
}
