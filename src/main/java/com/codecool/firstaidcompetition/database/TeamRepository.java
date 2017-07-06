package com.codecool.firstaidcompetition.database;

import com.codecool.firstaidcompetition.model.Team;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by keli on 2017.07.06..
 */
public interface TeamRepository extends CrudRepository<Team, Long> {}
