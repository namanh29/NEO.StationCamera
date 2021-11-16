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
@Table(name = "DISTRICTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class District {
    @Id
    @Column(name = "DISTRICT_ID")
    private Integer districtId;

    @Column(name = "DISTRICT_CODE")
    private String districtCode;

    @Column(name = "DISTRICT_NAME")
    private String districtName;
}
