package com.example.be.service;

import com.example.be.dto.CameraDisplay;
import com.example.be.entity.Camera;
import com.example.be.repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CameraService {
    private final CameraRepository cameraRepository;

    @Autowired
    public CameraService(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
    }

    public List<CameraDisplay> getCameras(String station_id) {
        return cameraRepository.findByStationId(station_id);

    }

    public void addCamera(Camera camera) {
        Integer id = cameraRepository.getMaxId() + 1;
        camera.setId(id);
        cameraRepository.save(camera);
    }

    public ResponseEntity<Object> updateCamera(Camera camera, Integer id){
        /*return cameraRepository.findById(id)
                .map(camera -> {
                    camera.setCameraId(newCamera.getCameraId());
                    camera.setCameraIp(newCamera.getCameraIp());
                    camera.setCameraName(newCamera.getCameraName());
                    camera.setPosition(newCamera.getPosition());
                    camera.setStatus(newCamera.getStatus());
                    camera.setUserLogin(newCamera.getUserLogin());
                    camera.setPassLogin(newCamera.getPassLogin());
                    return cameraRepository.save(camera);
                })
                .orElseGet(() -> {
                    newCamera.setId(id);
                    return cameraRepository.save(newCamera);
                });*/
        Optional<Camera> cameraData = cameraRepository.findById(id);
        if(!cameraData.isPresent()){
            return ResponseEntity.noContent().build();
        }
        camera.setId(id);
        cameraRepository.save(camera);
        return ResponseEntity.ok().build();
    }
}
