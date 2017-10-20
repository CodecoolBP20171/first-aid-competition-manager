package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public void save(Competition competition) {
        competitionRepository.save(competition);
    }
}
