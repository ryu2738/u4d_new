package com.chahoo.u4d.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * Created by jjryu on 2017-02-08.
 */
@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;

    private String code;

    private List<FieldError> errors;

    public ErrorResponse() {

    }

    // TODO
    public static class FieldError {
        private String field;
        private String value;
        private String reason;
    }

}
