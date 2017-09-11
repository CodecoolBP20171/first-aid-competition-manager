package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.model.Exercise;
import com.codecool.firstaidcompetition.repository.ExerciseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTMLDocument;
import java.util.List;

/**
 * Created by keli on 2017.09.11..
 */
@Controller
@RequestMapping("/exercise/")
public class ExerciseController {

    private static final Logger logger = LoggerFactory.getLogger(HTTPController.class.getName());

    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping("/table")
    private String listAllExercise(Model model){
        Iterable<Exercise> exerciseList = exerciseRepository.findAll();
        model.addAttribute("listOfExercises", exerciseList);
        return "exercises/exercise_table";
    }

    @GetMapping("/delete/{exerciseId}")
    private String deleteExercise(@PathVariable String exerciseId){
        logger.info("Deleted  exercise with id: {}", exerciseId);
        return "exercises/exercise_table";
    }
}
