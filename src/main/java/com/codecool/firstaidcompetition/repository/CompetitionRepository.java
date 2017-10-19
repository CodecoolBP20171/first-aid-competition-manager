package com.codecool.firstaidcompetition.repository;

import com.codecool.firstaidcompetition.model.Competition;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompetitionRepository extends CrudRepository<Competition, Long> {
    List<Competition> findCompetitionByName(String name);
}
