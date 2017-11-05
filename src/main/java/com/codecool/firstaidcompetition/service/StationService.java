package com.codecool.firstaidcompetition.service;

import com.codecool.firstaidcompetition.model.Station;
import com.codecool.firstaidcompetition.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationService {

    private final StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
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
        stationRepository.save(station);
    }

    public void delete(long id) {
        stationRepository.delete(id);
    }

}
