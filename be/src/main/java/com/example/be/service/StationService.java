package com.example.be.service;

import com.example.be.entity.Station;
import com.example.be.entity.StationDisplay;
import com.example.be.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    private final StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<StationDisplay> getStations() {
        return stationRepository.getStations();
    }
}