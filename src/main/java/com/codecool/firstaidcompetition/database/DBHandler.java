package com.codecool.firstaidcompetition.database;

import com.codecool.firstaidcompetition.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class DBHandler {

    private CompetitionRepository repository;
    private UserRepository userRepository;

    @Autowired
    public DBHandler(CompetitionRepository repository, UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Iterable<Competition> findAll(){
        return repository.findAll();
    }

    public void populateDB() throws ParseException {
        User user = new User("fullname", "username", "email", "pass");
        userRepository.save(user);

        // Date formatting example
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse("2012-07-08");

        Competition competition = new Competition("Első verseny", "Budapest", date, user);
        Competition competition2 = new Competition("Második verseny", "Budapest", date, user);
        Competition competition3 = new Competition("Harmadik verseny", "Budapest", date, user);
        repository.save(competition);
        repository.save(competition2);
        repository.save(competition3);
    }


}
