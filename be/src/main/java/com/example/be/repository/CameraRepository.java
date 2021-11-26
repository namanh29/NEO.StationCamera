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
            "and ((lower(c.cameraName) like lower(concat('%',?2,'%'))) or (?2 is null )) " +
            "and ((lower(i.insName) like lower(concat('%',?3,'%'))) or (?3 is null )) " +
            "and ((c.status = ?4) or (?4 is null )) " +
            "and ((lower(c.position) like lower(concat('%',?5,'%'))) or (?5 is null )) " +
            "order by (CASE WHEN c.updatedDate IS NULL THEN 0 ELSE 1 END) DESC, c.updatedDate DESC" )
    Page<CameraDisplay> getCamerasPagingAndFilter(String stationId, String cameraName, String insName, Integer status, String position, Pageable pageable);

    List<Camera> findByCameraIdAndStationIdAndCameraIpAndStatus(Integer cameraId, String stationId, String cameraIp, Integer status);
}
