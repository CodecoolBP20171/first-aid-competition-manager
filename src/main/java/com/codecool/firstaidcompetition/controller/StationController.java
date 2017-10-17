package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.service.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/station")
public class StationController {

    private static final Logger logger = LoggerFactory.getLogger(StationController.class);

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping(value = {"/", ""})
    public Iterable<Station> getAll() {
        logger.info("Get all stations from the db");
        return stationService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Station getById(@PathVariable long id) {
        logger.info("Get Station with id {} from the db", id);

        return stationService.findById(id);
    }

    @PostMapping(value = {"/", ""})
    public String save(@RequestBody Station station) {
        stationService.save(station);

        logger.info("Add Station to the db");
        return "OK";
    }

    @PutMapping(value = "/{id}")
    public String update(@PathVariable long id, @RequestBody Station station) {
        stationService.update(id, station);

        logger.info("Update Station with {} id in the db", id);
        return "OK";
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable long id) {
        stationService.delete(id);

        logger.info("Delete Station with {} id from the db", id);
        return "OK";
    }

}
