package com.chahoo.u4d.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jjryu on 2017-02-07.
 */
@Entity
@Data
//@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private Long serNo;
    @Column(unique = true, nullable = false)
    private String loginId;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    private String email;

    private String createLoginId;
    private String updateLoginId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Transient
    private boolean admin;

}
