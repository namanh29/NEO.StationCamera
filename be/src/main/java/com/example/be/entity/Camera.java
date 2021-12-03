package com.example.be.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotEmpty(message = "Station id không được để trống")
    @Column(name = "STATION_ID")
    private String stationId;

    @NotNull(message = "Camera id không được để trống")
    @Column(name = "CAMERA_ID")
    private Integer cameraId;

    @NotNull(message = "Trạng thái không được để trống")
    @Column(name = "STATUS")
    private Integer status;

    @NotEmpty(message = "Camera Ip không được để trống")
    @Column(name = "CAMERA_IP")
    private String cameraIp;

    @NotEmpty(message = "Vị trí lắp đặt không được để trống")
    @Column(name = "POSITION")
    private String position;

    @NotEmpty(message = "User đăng nhập không được để trống")
    @Column(name = "USER_LOGIN")
    private String userLogin;

    @NotEmpty(message = "Pass đăng nhập không được để trống")
    @Column(name = "PASS_LOGIN")
    private String passLogin;

    @NotEmpty(message = "Tên camera không được để trống")
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
