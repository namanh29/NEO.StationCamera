package com.example.be.api;

import com.example.be.entity.Instrumentation;
import com.example.be.service.InstrumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/instrumentation")
@CrossOrigin("http://localhost:8080/")
public class InstrumentationController {
    private final InstrumentationService instrumentationService;
    @Autowired
    public InstrumentationController(InstrumentationService instrumentationService) {
        this.instrumentationService = instrumentationService;
    }
    @GetMapping
    public List<Instrumentation> getInstrumentations(){
        return instrumentationService.getInstrumentations();
    }
}
