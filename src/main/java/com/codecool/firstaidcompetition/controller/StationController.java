package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.repository.CompetitionRepository;
import com.codecool.firstaidcompetition.repository.StationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/station")
public class StationController {

    private static final Logger logger = LoggerFactory.getLogger(StationController.class);

    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private CompetitionRepository competitionRepository;

    @RequestMapping(value = {"/" , ""})
    public String getStations(Model model) {
        Iterable<Station> stationList = stationRepository.findAll();
        model.addAttribute("listOfStations", stationList);

        logger.info("Mapping to the station route");
        return "station/station_table";
    }

    @GetMapping(value = "/add")
    public String addStation(Model model) {
        Iterable<Competition> competitionList = competitionRepository.findAll();
        model.addAttribute("listOfCompetitions", competitionList);
        model.addAttribute("station", new Station());
        return "station/station_form";
    }

    @PostMapping(value = "/add")
    public ModelAndView submitStation(@ModelAttribute Station station) {
        stationRepository.save(station);
        logger.info("Save station to the db, " +
                        "[name: {}; location: {}; date: {}, owner: {}]",
                station.getName(), station.getNumber(), station.getDescription(),
                station.getCompetition());
        return new ModelAndView("redirect:/station");
    }

    @GetMapping("/delete/{stationId}")
    private ModelAndView deleteStation(@PathVariable String stationId){
        stationRepository.delete(Long.valueOf(stationId));
        logger.info("Deleted thw following station id: {}", stationId);
        return new ModelAndView("redirect:/station");
    }

}
