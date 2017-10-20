package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.exception.StationNotFoundException;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.service.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/station")
public class StationController {

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping(value = {"/", ""})
    public Iterable<Station> getAll() {
        return stationService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Station getById(@PathVariable long id) throws StationNotFoundException {
        isValidStationId(id);
        Station station = stationService.findById(id);

        return station;
    }

    @PostMapping(value = {"/", ""})
    public ResponseEntity<String> save(@RequestBody Station station) {
        stationService.save(station);

        return new ResponseEntity<>("Created a new station", HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody Station station) throws StationNotFoundException {
        isValidStationId(id);
        stationService.update(id, station);

        return new ResponseEntity<>("Updated existing station", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) throws StationNotFoundException {
        isValidStationId(id);
        stationService.delete(id);

        return new ResponseEntity<>("Deleted station", HttpStatus.OK);
    }

    @ExceptionHandler(StationNotFoundException.class)
    public void handleStationNotFound(StationNotFoundException exception,
                                      HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    private void isValidStationId(long id) throws StationNotFoundException {
        Station station = stationService.findById(id);
        if (station == null) {
            throw new StationNotFoundException("Station with id: " + id + " not found!");
        }
    }

}
