package com.chahoo.u4d.api.buildInfo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by jjryu on 2017-02-07.
 */
public class BuildInfoDto {
    @Data
    public static class Create{
        private Long id;
        private String loginId;
        @Size(min=5)
        private String username;
        @NotBlank
        @Size(min=5)
        private String password;
    }

    @Data
    public static class Response{
        private long qrSerno;
        private String qrCode;
        private Long cnstSerno;
        private long prodSerno;
        private Long areaSerno;
        private String surpntMthCode;
        private String surpntNo;
        private char desgQrYn;
        private char buldYn;
        private char asTgtYn;
        private BigDecimal insLgtu;
        private BigDecimal insLatu;
        private BigDecimal insTmX;
        private BigDecimal insTmY;
        private BigDecimal insHeight;
        private BigDecimal insDepth;
        private String descr;
        private String builderNm;
        private BigDecimal rotX;
        private BigDecimal rotY;
        private BigDecimal rotZ;
        private BigDecimal ofsX;
        private BigDecimal ofsY;
        private BigDecimal ofsZ;
        private BigDecimal scale;
        private BigInteger skin;
        private char modelInfoYn;
        private char surveyYn;
        private BigDecimal scaleX;
        private BigDecimal scaleY;
        private BigDecimal scaleZ;
        private Date regDate;
    }

    @Data
    public static class Update {
        private String password;
        private String fullName;
    }
}
