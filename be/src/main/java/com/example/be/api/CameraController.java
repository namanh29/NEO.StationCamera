package com.example.be.api;

import com.example.be.dto.CameraDisplay;
import com.example.be.dto.PagingFilterResponse;
import com.example.be.entity.Camera;
import com.example.be.exception.ResourceNotFoundException;
import com.example.be.repository.CameraRepository;
import com.example.be.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/camera")
@CrossOrigin("http://localhost:8080/")
public class CameraController {
    private final CameraService cameraService;
    private final CameraRepository cameraRepository;

    @Autowired
    public CameraController(CameraService cameraService, CameraRepository cameraRepository) {
        this.cameraService = cameraService;
        this.cameraRepository = cameraRepository;
    }

    @GetMapping(path = "/station_id/{stationId}")
    public List<CameraDisplay> getCameras(@PathVariable("stationId") String station_id){
        return cameraService.getCameras(station_id);
    }

    @PostMapping
    public ResponseEntity<Camera> addCamera(@Valid @RequestBody Camera camera){
        return new ResponseEntity<>(cameraService.addCamera(camera), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Camera> updateCamera(@Valid @RequestBody Camera camera, @PathVariable Integer id) {
        return new ResponseEntity<>(cameraService.updateCamera(camera, id), HttpStatus.OK);
    }

    @GetMapping(path = "/station_id/{stationId}/filter")
    public PagingFilterResponse<CameraDisplay> getCameraFilter(
            @PathVariable("stationId") String station_id,
            @RequestParam(required = false, name = "name") String cameraName,
            @RequestParam(required = false, name = "ins") String insName,
            @RequestParam(required = false, name = "status") Integer status,
            @RequestParam(required = false, name = "position") String position,
            @RequestParam(name = "pageindex", defaultValue = "0") Integer pageIndex,
            @RequestParam(name = "pagesize", defaultValue = "10") Integer pageSize){

        Pageable paging = PageRequest.of(pageIndex, pageSize);
        Page<CameraDisplay> cameraResult = cameraRepository.getCamerasPagingAndFilter(station_id, cameraName, insName, status, position, paging);
        PagingFilterResponse<CameraDisplay> response = new PagingFilterResponse(cameraResult.getContent(), cameraResult.getTotalPages(), cameraResult.getTotalElements());
        return response;

    }


}
