package com.codecool.firstaidcompetition.repository;

import com.codecool.firstaidcompetition.model.Competition;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by keli on 2017.07.06..
 */
public interface CompetitionRepository extends CrudRepository<Competition, Integer>{

    List<Competition> findCompetitionByName(String name);

}
