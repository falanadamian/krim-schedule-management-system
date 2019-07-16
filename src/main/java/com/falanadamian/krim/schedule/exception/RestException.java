package com.falanadamian.krim.schedule.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class RestException {

    private LocalDateTime timestamp;
    private HttpStatus httpStatus;
    private String message;
    private List<String> errors;

    public RestException(HttpStatus httpStatus, String message, List<String> errors) {
        super();
        timestamp = LocalDateTime.now();
        this.httpStatus = httpStatus;
        this.message = message;
        this.errors = errors;
    }

    public RestException(HttpStatus httpStatus, String message, String error) {
        super();
        timestamp = LocalDateTime.now();
        this.httpStatus = httpStatus;
        this.message = message;
        this.errors = Arrays.asList(error);
    }

}
