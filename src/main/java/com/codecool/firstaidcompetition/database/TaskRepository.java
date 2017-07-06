package com.codecool.firstaidcompetition.database;

import com.codecool.firstaidcompetition.model.Task;
import com.codecool.firstaidcompetition.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by keli on 2017.07.06..
 */
public interface TaskRepository extends CrudRepository<Task, Long> {}

