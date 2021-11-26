package com.example.be.service;

import com.example.be.dto.CameraDisplay;
import com.example.be.entity.Camera;
import com.example.be.exception.ResourceDuplicateException;
import com.example.be.exception.ResourceNotFoundException;
import com.example.be.repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Camera addCamera(Camera camera) {
        List<Camera> existingCamera = cameraRepository.findByCameraIdAndStationIdAndCameraIpAndStatus(
                camera.getCameraId(),
                camera.getStationId(),
                camera.getCameraIp(),
                camera.getStatus() );
        if(!existingCamera.isEmpty()) {
            throw new ResourceDuplicateException("Camera đã tồn tại");
        }
        else {
            Integer id = cameraRepository.getMaxId() + 1;
            camera.setId(id);
            camera.setCreatedDate(new Date());
            camera.setUpdatedDate(new Date());
            return cameraRepository.save(camera);
        }
    }

    public Camera updateCamera(Camera camera, Integer id){
        Optional<Camera> existingCamera = cameraRepository.findById(id);
        if(!existingCamera.isPresent()){
            throw new ResourceNotFoundException("Camera", "Id", id);
        }
        else {
            camera.setId(id);
            camera.setUpdatedDate(new Date());
            return cameraRepository.save(camera);
        }

    }
}
