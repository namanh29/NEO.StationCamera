package com.example.be.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StationDisplay {
    private String stationId;
    private String objectType;
    private String objectTypeShortname;
    private String stationCode;
    private String stationName;
    private String longtitude;
    private String latitude;
    private String provinceName;
    private String districtName;
    private String wardName;
    private String riverName;
    private String address;
    private Integer isActive;
}
