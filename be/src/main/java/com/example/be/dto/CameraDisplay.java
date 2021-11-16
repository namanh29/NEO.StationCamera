package com.example.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CameraDisplay {
    private Integer id;
    private Integer cameraId;
    private String cameraName;
    private String cameraIp;
    private String position;
    private Integer status;
    private String statusName;
    private String userLogin;
    private String passLogin;
    private String insCode;
    private String insName;
    private String manufact;

    public CameraDisplay(Integer id, Integer cameraId, String cameraName, String cameraIp, String position, Integer status, String userLogin, String passLogin, String insCode, String insName, String manufact) {
        this.id = id;
        this.cameraId = cameraId;
        this.cameraName = cameraName;
        this.cameraIp = cameraIp;
        this.position = position;
        this.status = status;
        if(status == 1){
            this.statusName = "dang dung";
        }
        else if(status == 2){
            this.statusName = "dang loi";
        }

        this.userLogin = userLogin;
        this.passLogin = passLogin;
        this.insCode = insCode;
        this.insName = insName;
        this.manufact = manufact;
    }
    //private Integer isObservable;
}
