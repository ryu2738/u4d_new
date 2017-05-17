package com.chahoo.u4d.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

/**
 * Created by jjryu on 2017-02-15.
 */
@Entity
@Data
public class Qr {
    @Id
    private long qrSerno;
    private String qrCode;
    private Long cnstSerno;
    private long prodSerno;
    private Long areaSerno;
    private String surpntMthCode;
    private String surpntNo;
    private Character desgQrYn;
    private Character buldYn;
    private Character asTgtYn;
    private BigDecimal insLgtu;
    private BigDecimal insLatu;
    @Column(name="ins_tm_x")
    private BigDecimal insTmX;
    @Column(name="ins_tm_y")
    private BigDecimal insTmY;
    private BigDecimal insHeight;
    private BigDecimal insDepth;
    private String descr;
    private Long regUserSerno;
    private Long updtUserSerno;
    private String builderNm;
    @Column(name="rot_x")
    private BigDecimal rotX;
    @Column(name="rot_y")
    private BigDecimal rotY;
    @Column(name="rot_z")
    private BigDecimal rotZ;
    @Column(name="ofs_x")
    private BigDecimal ofsX;
    @Column(name="ofs_y")
    private BigDecimal ofsY;
    @Column(name="ofs_z")
    private BigDecimal ofsZ;
    private BigDecimal scale;
    private BigInteger skin;
    private Character modelInfoYn;
    private Character surveyYn;
    @Column(name="scale_x")
    private BigDecimal scaleX;
    @Column(name="scale_y")
    private BigDecimal scaleY;
    @Column(name="scale_z")
    private BigDecimal scaleZ;
    private Character updtYn;

    //@Temporal(TemporalType.TIMESTAMP)
    private Date buldDate;
    //@Temporal(TemporalType.TIMESTAMP)
    private Date regDate;
    //@Temporal(TemporalType.TIMESTAMP)
    private Date updtDate;
    private Character useYn;
}
