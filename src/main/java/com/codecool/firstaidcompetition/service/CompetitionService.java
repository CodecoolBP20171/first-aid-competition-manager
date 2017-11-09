package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.repository.CompetitionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitionService {

    private static final Logger logger = LoggerFactory.getLogger(CompetitionService.class);

    private final CompetitionRepository competitionRepository;
    private final UserServiceImpl userService;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository, UserServiceImpl userService) {
        this.competitionRepository = competitionRepository;
        this.userService = userService;
    }

    public void save(Competition competition) {
        competitionRepository.save(competition);
    }

    public Iterable<Competition> getAllCompetitionsService() {
        return competitionRepository.findAll();
    }

    public Competition getCompetitionByIdService(long id) {
        return competitionRepository.findOne(id);
    }

    public void addNewCompetitionService(Competition competition) {
        // Currently the authentication from DB is out of order
//        User authenticatedUser = userService.getAuthenticatedUser();
//        competition.setOwner(authenticatedUser);
        competition.setOwner(null);

        competitionRepository.save(competition);
        logger.info("Save competition to the db, " +
                        "[name: {}; location: {}; date: {}, owner: {}]",
                competition.getName(), competition.getLocation(), competition.getDateOfEvent(),
                competition.getOwner());
    }

    public void editCompetitionService(long id, Competition competition) {
        competition.setId(id);
        competitionRepository.save(competition);
        logger.info("Edited competition with id: " + competition.getId());
    }

    public void deleteCompetitionService(long id) {
        Competition competition = competitionRepository.findOne(id);
        competitionRepository.delete(competition);
        logger.info("Deleted competition with id: " + id);
    }
}
