package com.chahoo.u4d.entity;

import com.chahoo.u4d.exception.InvalidCreateFieldException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by jjryu on 2017-02-28.
 */
@Data
public class Production extends BaseEntity {
    @NotBlank
    protected String productKind;//제품종류
    @NotBlank
    protected String prodNm;
}
