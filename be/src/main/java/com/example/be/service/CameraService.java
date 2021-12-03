package com.example.be.service;

import com.example.be.dto.CameraDisplay;
import com.example.be.entity.Camera;
import com.example.be.entity.LogsAction;
import com.example.be.exception.ResourceDuplicateException;
import com.example.be.exception.ResourceNotFoundException;
import com.example.be.repository.CameraRepository;
import com.example.be.repository.LogsActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CameraService {
    private final CameraRepository cameraRepository;
    private final LogsActionRepository logsActionRepository;

    @Autowired
    public CameraService(CameraRepository cameraRepository, LogsActionRepository logsActionRepository) {
        this.cameraRepository = cameraRepository;
        this.logsActionRepository = logsActionRepository;
    }

    public List<CameraDisplay> getCameras(String station_id) {
        return cameraRepository.findByStationId(station_id);

    }

    public Camera addCamera(Camera camera) {
        List<Camera> existingCamera = cameraRepository.findByCameraIdAndStationIdAndCameraIpAndStatus(
                camera.getCameraId(),
                camera.getStationId(),
                camera.getCameraIp(),
                camera.getStatus());
        if (!existingCamera.isEmpty()) {
            throw new ResourceDuplicateException("Camera đã tồn tại");
        } else {
            Integer id = cameraRepository.getMaxId() + 1;
            camera.setId(id);
            Date date = new Date();
            camera.setCreatedDate(date);
            camera.setUpdatedDate(date);
            cameraRepository.save(camera);

            Integer logId = logsActionRepository.getMaxId() + 1;
            logsActionRepository.save(new LogsAction(
                    logId,
                    "Quản lý camera của từng trạm",
                    "ADD",
                    "administrator",
                    date,
                    "STATION_CAMERAS"));
            return camera;
        }
    }

    public Camera updateCamera(Camera camera, Integer id) {
        Optional<Camera> existingCamera = cameraRepository.findById(id);
        if (!existingCamera.isPresent()) {
            throw new ResourceNotFoundException("Camera", "Id", id);
        }
        List<Camera> duplicateCamera = cameraRepository.findByCameraIdAndStationIdAndCameraIpAndStatusAndIdNot(
                camera.getCameraId(),
                camera.getStationId(),
                camera.getCameraIp(),
                camera.getStatus(),
                id);
        if (!duplicateCamera.isEmpty()) {
            throw new ResourceDuplicateException("Camera đã tồn tại");
        }

        camera.setId(id);
        Date date = new Date();
        camera.setUpdatedDate(date);
        cameraRepository.save(camera);


        Integer logId = logsActionRepository.getMaxId() + 1;
        logsActionRepository.save(new LogsAction(
                logId,
                "Quản lý camera của từng trạm",
                "UPDATE",
                "administrator",
                date,
                "STATION_CAMERAS"));
        return camera;


    }
}
