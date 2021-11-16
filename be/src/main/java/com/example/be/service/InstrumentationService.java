package com.example.be.service;

import com.example.be.entity.Instrumentation;
import com.example.be.repository.InstrumentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentationService {
    private final InstrumentationRepository instrumentationRepository;

    @Autowired
    public InstrumentationService(InstrumentationRepository instrumentationRepository) {
        this.instrumentationRepository = instrumentationRepository;
    }

    public List<Instrumentation> getInstrumentations(){
        return instrumentationRepository.getInstrumentations();
    }
}
