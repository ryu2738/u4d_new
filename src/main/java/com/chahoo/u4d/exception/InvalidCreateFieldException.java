package com.chahoo.u4d.exception;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Created by jjryu on 2017-02-28.
 */
public class InvalidCreateFieldException extends U4DException {
    public InvalidCreateFieldException(String message) {
        super(message);
    }
}
