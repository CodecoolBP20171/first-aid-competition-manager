package com.codecool.firstaidcompetition.repository;

import com.codecool.firstaidcompetition.model.Station;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by keli on 2017.07.06..
 */
public interface StationRepository extends CrudRepository<Station, Long> {
    List<Station> findAllByExerciseIsNull();
}
