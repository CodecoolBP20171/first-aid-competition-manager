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
import java.util.Arrays;
import java.util.List;

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
        model.addAttribute("editedExercise", new Exercise());
        return "exercises/exercise_table";
    }

    @PostMapping("/table")
    private ModelAndView editexercise(@ModelAttribute Exercise exercise){
        Exercise editedExercise = exerciseRepository.findOne(exercise.getId());
        editedExercise.setName(exercise.getName());
        editedExercise.setDescription(exercise.getDescription());
        exerciseRepository.save(editedExercise);
        return new ModelAndView("redirect:/exercise/table");
    }

    @GetMapping("/delete/{exerciseId}")
    private ModelAndView deleteExercise(@PathVariable String exerciseId){
        exerciseRepository.delete(Long.valueOf(exerciseId));
        logger.info("Deleted  exercise with id: {}", exerciseId);
        return new ModelAndView("redirect:/exercise/table");
    }

    @GetMapping("/add")
    private String addExercise(Model model){
        model.addAttribute("exercise", new Exercise());
        model.addAttribute("listOfStations", stationRepository.findAll());
        return "exercises/exercise_form";
    }

    @PostMapping("/add")
    private ModelAndView saveExercise(@ModelAttribute Exercise exercise){
        exerciseRepository.save(exercise);
        Station station = stationRepository.findOne(exercise.getStation().getId());
        station.addExercise(exercise);
        stationRepository.save(station);
        return new ModelAndView("redirect:/exercise/table");
    }

    @GetMapping("/edit/{exerciseId}")
    @ResponseBody
    private Exercise editExercise(@PathVariable String exerciseId){
        return exerciseRepository.findOne(Long.valueOf(exerciseId));
    }
}
