package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.model.Exercise;
import com.codecool.firstaidcompetition.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public Iterable<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    public Exercise findById(long id) {
        return exerciseRepository.findOne(id);
    }

    public void save(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    public void delete(long id) {
        exerciseRepository.delete(id);
    }

    public void update(long id, Exercise exercise) {
        exercise.setId(id);
        exerciseRepository.save(exercise);
    }

}
