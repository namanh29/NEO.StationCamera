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
@Table(name = "WARDS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ward {
    @Id
    @Column(name = "WARD_ID")
    private Integer wardId;

    @Column(name = "WARD_CODE")
    private String wardCode;

    @Column(name = "WARD_NAME")
    private String wardName;
}
