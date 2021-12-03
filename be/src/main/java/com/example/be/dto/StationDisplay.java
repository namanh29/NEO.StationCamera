package com.example.be.dto;

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
    private String isActiveText;

    public StationDisplay(String stationId, String objectType, String objectTypeShortname, String stationCode, String stationName, String longtitude, String latitude, String provinceName, String districtName, String wardName, String riverName, String address, Integer isActive) {
        this.stationId = stationId;
        this.objectType = objectType;
        this.objectTypeShortname = objectTypeShortname;
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.provinceName = provinceName;
        this.districtName = districtName;
        this.wardName = wardName;
        this.riverName = riverName;
        this.address = address;
        this.isActive = isActive;
        if(this.isActive == 1) this.isActiveText = "Đang hoạt động";
        else this.isActiveText = "Không hoạt động";
    }
}
