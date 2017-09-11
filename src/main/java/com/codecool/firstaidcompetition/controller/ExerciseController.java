package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.model.Exercise;
import com.codecool.firstaidcompetition.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.HTMLDocument;
import java.util.List;

/**
 * Created by keli on 2017.09.11..
 */
@Controller
@RequestMapping("/exercise/")
public class ExerciseController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping("/table")
    private String listAllExercise(Model model){
        Iterable<Exercise> exerciseList = exerciseRepository.findAll();
        model.addAttribute("listOfExercises", exerciseList);
        return "exercises/exercise_table";
    }
}
