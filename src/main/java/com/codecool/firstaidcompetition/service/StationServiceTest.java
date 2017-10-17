package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.model.Competition;
import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationServiceTest {

    private final StationRepository stationRepository;
    private final CompetitionService competitionService;

    @Autowired
    public StationServiceTest(StationRepository stationRepository, CompetitionService competitionService) {
        this.stationRepository = stationRepository;
        this.competitionService = competitionService;
    }

    public Iterable<Station> findAll() {
        return stationRepository.findAll();
    }

    public Station findById(long id) {
        return stationRepository.findOne(id);
    }

    public void save(Station station) {
        stationRepository.save(station);
    }

    public void update(long id, Station station) {
        station.setId(id);

        Competition currentComp = station.getCompetition();
        competitionService.save(currentComp);
        stationRepository.save(station);
    }

    public void delete(long id) {
        stationRepository.delete(id);
    }

}
