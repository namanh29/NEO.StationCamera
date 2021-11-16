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
@Table(name = "RIVERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class River {
    @Id
    @Column(name = "RIVER_ID")
    private Integer riverId;

    @Column(name = "RIVER_CODE")
    private String riverCode;

    @Column(name = "RIVER_NAME")
    private String riverName;
}
