package com.codecool.firstaidcompetition.repository;

import com.codecool.firstaidcompetition.model.Exercise;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
    Iterable<Exercise> findByStationId(Long stationId);
}
