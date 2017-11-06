package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.exception.ExerciseNotFoundException;
import com.codecool.firstaidcompetition.model.Exercise;
import com.codecool.firstaidcompetition.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping(value = {"/", ""})
    private Iterable<Exercise> getAll() {
        return exerciseService.findAll();
    }

    @GetMapping("/{id}")
    public Exercise getById(@PathVariable long id) throws ExerciseNotFoundException {
        isValidId(id);

        return exerciseService.findById(id);
    }

    @PostMapping(value = {"/", ""})
    public ResponseEntity<String> save(@RequestBody Exercise exercise) {
        exerciseService.save(exercise);
        return new ResponseEntity<>("Created a new Exercise entity", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) throws ExerciseNotFoundException {
        isValidId(id);

        exerciseService.delete(id);
        return new ResponseEntity<>("Deleted an Exercise entity", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody Exercise exercise) throws ExerciseNotFoundException {
        isValidId(id);

        exerciseService.update(id, exercise);
        return new ResponseEntity<>("Updated an Exercise entity", HttpStatus.OK);
    }

    @ExceptionHandler(ExerciseNotFoundException.class)
    public void handleExerciseNotFound(ExerciseNotFoundException exception,
                                       HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, exception.getMessage());
    }

    private void isValidId(long id) throws ExerciseNotFoundException {
        Exercise exercise = exerciseService.findById(id);
        if (exercise == null) {
            throw new ExerciseNotFoundException("Exercise with id: " + id + " not found!");
        }
    }
}
