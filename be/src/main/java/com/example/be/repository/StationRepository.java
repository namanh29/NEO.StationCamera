package com.example.be.repository;

import com.example.be.entity.Station;
import com.example.be.entity.StationDisplay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, String> {
    //@Query("select new  com.example.be.entity.StationDisplay(st.objectType,st.objectTypeShortname,s.stationCode,s.stationName,s.longtitude,s.latitude,s.address,p.provinceName,d.districtName,w.wardName,r.riverName, s.isActive) from Station s, StationType st, Province p, District d, Ward w, River r where ((s.stationTypeId = st.objectTypeId) and(s.provinceId=p.provinceId) and(s.districtId=d.districtId) and(s.wardId=w.wardId) and(s.riverId=r.riverId)) ")
    @Query("select new  com.example.be.entity.StationDisplay(" +
            "s.stationId,st.objectType,st.objectTypeShortname,s.stationCode,s.stationName,s.longtitude,s.latitude,p.provinceName,d.districtName, w.wardName,r.riverName,s.address, s.isActive) " +
            "from Station s left join StationType st on s.stationTypeId = st.objectTypeId " +
            "left join Province p on s.provinceId = p.provinceId " +
            "left join District d on s.districtId = d.districtId " +
            "left join Ward w on s.wardId = w.wardId " +
            "left join River r on s.riverId = r.riverId ")
    List<StationDisplay> getStations();
}
