package com.example.be.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STATION_CAMERAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Camera {
    @Id

    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "STATION_ID")
    private String stationId;

    @Column(name = "CAMERA_ID")
    private Integer cameraId;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "CAMERA_IP")
    private String cameraIp;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "USER_LOGIN")
    private String userLogin;

    @Column(name = "PASS_LOGIN")
    private String passLogin;

    @Column(name = "CAMERA_NAME")
    private String cameraName;

    @Column(name = "IS_OBSERVABLE")
    private Integer isObservable;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "UPDATED_BY")
    private String updatedBy;


}
