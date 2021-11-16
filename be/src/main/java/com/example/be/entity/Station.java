package com.example.be.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATIONS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {
    @Id
    @Column(name = "STATION_ID")
    private String stationId;

    @Column(name = "STATION_CODE")
    private String stationCode;

    @Column(name = "STATION_NAME")
    private String stationName;

    @Column(name = "LONGTITUDE")
    private String longtitude;

    @Column(name = "LATITUDE")
    private String latitude;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "STATION_TYPE_ID")
    private Integer stationTypeId;

    @Column(name = "PROVINCE_ID")
    private Integer provinceId;

    @Column(name = "DISTRICT_ID")
    private Integer districtId;

    @Column(name = "WARD_ID")
    private Integer wardId;

    @Column(name = "RIVER_ID")
    private Integer riverId;

    @Column(name = "IS_ACTIVE")
    private Integer isActive;


}
