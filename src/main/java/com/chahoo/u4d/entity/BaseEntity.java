package com.chahoo.u4d.entity;

import com.chahoo.u4d.exception.InvalidCreateFieldException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by jjryu on 2017-03-07.
 */
@Data
public class BaseEntity {
    protected String updateLoginId;
    protected String createLoginId;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updateDate;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createDate;


    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator      = factory.getValidator();

    @PrePersist
    public <T extends BaseEntity> void checkValidation(T obj) throws InvalidCreateFieldException, JsonProcessingException {
        Set<ConstraintViolation<T>> violations = validator.validate(obj);
        if(violations.size()>0) {
            String error=makeExceptionMessage(violations);
            throw new InvalidCreateFieldException(error);
        }
    }

    private <T extends BaseEntity> String  makeExceptionMessage(Set<ConstraintViolation<T>> violations) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        ArrayList result=new ArrayList();
        for (ConstraintViolation<T> violation : violations) {
            HashMap hash= new HashMap();
            hash.put(violation.getPropertyPath(), violation.getMessage());
            result.add(hash);
        }
        return objectMapper.writeValueAsString(result);

    }
}
