package com.example.be.api;

import com.example.be.dto.CameraDisplay;
import com.example.be.dto.PagingFilterCameraResponse;
import com.example.be.entity.Camera;
import com.example.be.repository.CameraRepository;
import com.example.be.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void addCamera(@RequestBody Camera camera){
        cameraService.addCamera(camera);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCamera(@RequestBody Camera newCamera, @PathVariable Integer id) {
        return cameraService.updateCamera(newCamera, id);
    }

    @GetMapping(path = "/station_id/{stationId}/filter")
    public Page<CameraDisplay> getCameraFilter(
            @PathVariable("stationId") String station_id,
            @RequestParam(required = false, name = "name") String cameraName,
            @RequestParam(name = "pageindex", defaultValue = "0") Integer pageIndex,
            @RequestParam(name = "pagesize", defaultValue = "10") Integer pageSize){

        Pageable paging = PageRequest.of(pageIndex, pageSize);
        Page<CameraDisplay> cameraResult = cameraRepository.getCamerasPagingAndFilter(station_id, cameraName, paging);
        /*PagingFilterCameraResponse response = new PagingFilterCameraResponse(cameraResult.getContent(), cameraResult.getTotalPages(), cameraResult.getTotalElements());
        return response;*/
        return cameraResult;

    }

    /*@GetMapping(path = "/station_id/{stationId}/filter")
    public PagingFilterCameraResponse getCameraFilter(
            @PathVariable("stationId") String station_id,
            @RequestParam(required = false, name = "name") String cameraName,
            @RequestParam(name = "pageindex", defaultValue = "0") Integer pageIndex,
            @RequestParam(name = "pagesize", defaultValue = "10") Integer pageSize){

        Pageable paging = PageRequest.of(pageIndex, pageSize);
        //Page<CameraDisplay> cameraResult = cameraRepository.getCamerasPagingAndFilter(station_id, cameraName, paging);
        //PagingFilterCameraResponse response = new PagingFilterCameraResponse(cameraResult.getContent(), cameraResult.getTotalPages(), cameraResult.getTotalElements());
        List<CameraDisplay> response = cameraRepository.getCamerasPagingAndFilter(station_id, cameraName);
        return response;

    }*/

}
