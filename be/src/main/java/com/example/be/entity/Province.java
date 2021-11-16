package com.example.be.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROVINCES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Province {
    @Id
    @Column(name = "PROVINCE_ID")
    private Integer provinceId;

    @Column(name = "PROVINCE_CODE")
    private String provinceCode;

    @Column(name = "PROVINCE_NAME")
    private String provinceName;
}
