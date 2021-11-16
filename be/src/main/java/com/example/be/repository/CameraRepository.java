package com.example.be.repository;

import com.example.be.dto.CameraDisplay;
import com.example.be.entity.Camera;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Integer> {
    //List<Camera> findByStationId(String station_id);
    @Query("select new com.example.be.dto.CameraDisplay(" +
            "c.id, c.cameraId, c.cameraName, c.cameraIp, c.position, c.status, c.userLogin, c.passLogin, i.insCode, i.insName, i.manufact ) " +
            "from Camera c left join Instrumentation i on c.cameraId = i.insId " +
            "where c.stationId = ?1")
    List<CameraDisplay> findByStationId(String stationId);

    @Query("select max(c.id) from Camera c")
    Integer getMaxId();

    @Query("select new com.example.be.dto.CameraDisplay(" +
            "c.id, c.cameraId, c.cameraName, c.cameraIp, c.position, c.status, c.userLogin, c.passLogin, i.insCode, i.insName, i.manufact ) " +
            "from Camera c left join Instrumentation i on c.cameraId = i.insId " +
            "where c.stationId = ?1 " +
            "and ((c.cameraName like concat('%',?2,'%')) or (?2 is null )) ")
    Page<CameraDisplay> getCamerasPagingAndFilter(String stationId, String cameraName, Pageable pageable);
}
