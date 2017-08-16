package com.codecool.firstaidcompetition.repository;

import com.codecool.firstaidcompetition.model.Task;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by keli on 2017.07.06..
 */
public interface TaskRepository extends CrudRepository<Task, Long> {}

