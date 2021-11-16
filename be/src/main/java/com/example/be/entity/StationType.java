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
@Table(name = "OBJECT_TYPE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StationType {
    @Id
    @Column(name = "OBJECT_TYPE_ID")
    private Integer objectTypeId;

    @Column(name = "OBJECT_TYPE")
    private String objectType;

    @Column(name = "OBJECT_TYPE_SHORTNAME")
    private String objectTypeShortname;

}
