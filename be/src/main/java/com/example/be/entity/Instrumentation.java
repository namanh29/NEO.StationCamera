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
@Table(name = "INSTRUMENTATIONS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Instrumentation {
    @Id
    @Column(name = "INS_ID")
    private Integer insId;

    @Column(name = "INS_CODE")
    private String insCode;

    @Column(name = "INS_NAME")
    private String insName;

    @Column(name = "MANUFACT")
    private String manufact;

    @Column(name = "INS_MODEL")
    private String insModel;

    @Column(name = "IS_OBSERVABLE")
    private Integer isObservable;
}
