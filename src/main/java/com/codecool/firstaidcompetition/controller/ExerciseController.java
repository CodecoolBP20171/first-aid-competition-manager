package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.model.Exercise;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.repository.ExerciseRepository;
import com.codecool.firstaidcompetition.repository.StationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keli on 2017.09.11..
 */
@Controller
@RequestMapping("/exercise/")
public class ExerciseController {

    private static final Logger logger = LoggerFactory.getLogger(ExerciseController.class);

    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private StationRepository stationRepository;

    @GetMapping("/table")
    private String listAllExercise(Model model){
        Iterable<Exercise> exerciseList = exerciseRepository.findAll();
        model.addAttribute("listOfExercises", exerciseList);
        return "exercises/exercise_table";
    }

    @GetMapping("/delete/{exerciseId}")
    private String deleteExercise(@PathVariable String exerciseId){
        exerciseRepository.delete(Long.valueOf(exerciseId));
        logger.info("Deleted  exercise with id: {}", exerciseId);
        return "exercises/exercise_table";
    }

    @GetMapping("/add")
    private String addExercise(Model model){
        model.addAttribute("exercise", new Exercise());
        model.addAttribute("listOfStations", stationRepository.findAll());
        return "exercises/exercise_add";
    }

    @PostMapping("/add")
    private ModelAndView saveExercise(@ModelAttribute Exercise exercise){
        exerciseRepository.save(exercise);
        Station station = stationRepository.findOne(exercise.getStation().getId());
        station.addExercise(exercise);
        stationRepository.save(station);
        return new ModelAndView("redirect:/index");
    }
}
