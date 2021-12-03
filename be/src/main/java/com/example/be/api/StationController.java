package com.example.be.api;

import com.example.be.dto.CameraDisplay;
import com.example.be.dto.PagingFilterResponse;
import com.example.be.dto.StationDisplay;
import com.example.be.repository.StationRepository;
import com.example.be.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/station")
@CrossOrigin("http://localhost:8080/")
public class StationController {
    private final StationService stationService;
    private final StationRepository stationRepository;

    @Autowired
    public StationController(StationService stationService, StationRepository stationRepository) {
        this.stationService = stationService;
        this.stationRepository = stationRepository;
    }

    @GetMapping
    public List<StationDisplay> getStations(){
        return stationService.getStations();
    }

    @GetMapping(path = "/filter")
    public PagingFilterResponse<StationDisplay> getCameraFilter(
            @RequestParam(required = false, name = "objectType") String objectType,
            @RequestParam(required = false, name = "objectTypeShortname") String objectTypeShortname,
            @RequestParam(required = false, name = "stationCode") String stationCode,
            @RequestParam(required = false, name = "stationName") String stationName,
            @RequestParam(required = false, name = "longtitude") String longtitude,
            @RequestParam(required = false, name = "latitude") String latitude,
            @RequestParam(required = false, name = "provinceName") String provinceName,
            @RequestParam(required = false, name = "districtName") String districtName,
            @RequestParam(required = false, name = "wardName") String wardName,
            @RequestParam(required = false, name = "riverName") String riverName,
            @RequestParam(required = false, name = "address") String address,
            @RequestParam(required = false, name = "isActive") Integer isActive,
            @RequestParam(name = "pageindex", defaultValue = "0") Integer pageIndex,
            @RequestParam(name = "pagesize", defaultValue = "10") Integer pageSize){

        Pageable paging = PageRequest.of(pageIndex, pageSize);
        Page<StationDisplay> stationResult = stationRepository.getStationsPagingAndFilter(objectType, objectTypeShortname, stationCode, stationName,  longtitude,  latitude,  provinceName,  districtName,  wardName,  riverName,  address,  isActive,  paging);
        PagingFilterResponse<StationDisplay> response = new PagingFilterResponse(stationResult.getContent(), stationResult.getTotalPages(), stationResult.getTotalElements());
        return response;
    }
}
