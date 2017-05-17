package com.chahoo.u4d.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jjryu on 2017-03-07.
 */
@Entity
@Data
public class ObstKind extends BaseEntity{
    @Id
    @GeneratedValue
    private Long obstSerno;
    @NotBlank
    private String obstNm;
    @NotBlank()
    private String obstColor;
}
