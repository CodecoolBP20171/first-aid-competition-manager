package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.controller.CompetitionController;
import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.User;
import com.codecool.firstaidcompetition.repository.CompetitionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CompetitionService {

    private static final Logger logger = LoggerFactory.getLogger(CompetitionController.class);

    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private UserServiceImpl userService;

    public CompetitionService() {
    }

    public Iterable<Competition> getAllCompetitionsService() {
        return competitionRepository.findAll();
    }

    public void addNewCompetitionService(Competition competition) {
        User authenticatedUser = userService.getAuthenticatedUser();
        competition.setOwner(authenticatedUser);
        competitionRepository.save(competition);
        logger.info("Save competition to the db, " +
                        "[name: {}; location: {}; date: {}, owner: {}]",
                competition.getName(), competition.getLocation(), competition.getDateOfEvent(),
                competition.getOwner());
    }

    public void editCompetitionService(Competition competition) {
        Competition competitionEdit = competitionRepository.findOne(competition.getId());
        competitionEdit.setName(competition.getName());
        competitionEdit.setLocation(competition.getLocation());
        //Convert date to string
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = competition.getDateOfEvent();
        String compDate = df.format(date);
        competitionEdit.setDateOfEvent(compDate);
        competitionRepository.save(competitionEdit);
        logger.info("Edited competition with id: " + competitionEdit.getId());
    }

    public void deleteCompetitionService(long id) {
        Competition competition = competitionRepository.findOne(id);
        competitionRepository.delete(competition);
        logger.info("Deleted competition with id: " + id);
    }
}
