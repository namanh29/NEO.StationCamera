package com.example.be.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "LOGS_ACTION_DETAIL")
public class LogsAction {
    @Id
    @Column(name = "INS_PARAM_ID", nullable = false)
    private Integer insParamId;


    @Column(name = "MENU_NAME")
    private String menuName;

    @Column(name = "ACTION")
    private String action;

    @Column(name = "USER_ACT")
    private String userAct;

    @Column(name = "DATE_ACT", nullable = false)
    private Date dateAct;

    @Column(name = "TABLE_NAME")
    private String tableName;
}
