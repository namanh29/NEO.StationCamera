package com.example.be.repository;

import com.example.be.dto.CameraDisplay;
import com.example.be.entity.Station;
import com.example.be.dto.StationDisplay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, String> {
    //@Query("select new  com.example.be.dto.StationDisplay(st.objectType,st.objectTypeShortname,s.stationCode,s.stationName,s.longtitude,s.latitude,s.address,p.provinceName,d.districtName,w.wardName,r.riverName, s.isActive) from Station s, StationType st, Province p, District d, Ward w, River r where ((s.stationTypeId = st.objectTypeId) and(s.provinceId=p.provinceId) and(s.districtId=d.districtId) and(s.wardId=w.wardId) and(s.riverId=r.riverId)) ")
    @Query("select new  com.example.be.dto.StationDisplay(" +
            "s.stationId,st.objectType,st.objectTypeShortname,s.stationCode,s.stationName,s.longtitude,s.latitude,p.provinceName,d.districtName, w.wardName,r.riverName,s.address, s.isActive) " +
            "from Station s left join StationType st on s.stationTypeId = st.objectTypeId " +
            "left join Province p on s.provinceId = p.provinceId " +
            "left join District d on s.districtId = d.districtId " +
            "left join Ward w on s.wardId = w.wardId " +
            "left join River r on s.riverId = r.riverId ")
    List<StationDisplay> getStations();

    @Query("select new  com.example.be.dto.StationDisplay(" +
            "s.stationId,st.objectType,st.objectTypeShortname,s.stationCode,s.stationName,s.longtitude,s.latitude,p.provinceName,d.districtName, w.wardName,r.riverName,s.address, s.isActive) " +
            "from Station s left join StationType st on s.stationTypeId = st.objectTypeId " +
            "left join Province p on s.provinceId = p.provinceId " +
            "left join District d on s.districtId = d.districtId " +
            "left join Ward w on s.wardId = w.wardId " +
            "left join River r on s.riverId = r.riverId " +
            "where ((lower(st.objectType) like lower(concat('%',?1,'%'))) or (?1 is null )) " +
            "and ((lower(st.objectTypeShortname) like lower(concat('%',?2,'%'))) or (?2 is null )) " +
            "and ((lower(s.stationCode) like lower(concat('%',?3,'%'))) or (?3 is null )) " +
            "and ((lower(s.stationName) like lower(concat('%',?4,'%'))) or (?4 is null )) " +
            "and ((lower(s.longtitude) like lower(concat('%',?5,'%'))) or (?5 is null )) " +
            "and ((lower(s.latitude) like lower(concat('%',?6,'%'))) or (?6 is null )) " +
            "and ((lower(p.provinceName) like lower(concat('%',?7,'%'))) or (?7 is null )) " +
            "and ((lower(d.districtName) like lower(concat('%',?8,'%'))) or (?8 is null )) " +
            "and ((lower(w.wardName) like lower(concat('%',?9,'%'))) or (?9 is null )) " +
            "and ((lower(r.riverName) like lower(concat('%',?10,'%'))) or (?10 is null )) " +
            "and ((lower(s.address) like lower(concat('%',?11,'%'))) or (?11 is null )) " +
            "and ((s.isActive = ?12) or (?12 is null )) ")
    Page<StationDisplay> getStationsPagingAndFilter(String objectType, String objectTypeShortname, String stationCode, String stationName, String longtitude, String latitude, String provinceName, String districtName, String wardName, String riverName, String address, Integer isActive, Pageable pageable);
}
