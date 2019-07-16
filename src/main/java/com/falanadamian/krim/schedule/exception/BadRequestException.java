package com.falanadamian.krim.schedule.exception;

public class BadRequestException extends RuntimeException{

    public BadRequestException() {
        super("Bad request.");
    }

    public BadRequestException(String message) {
        super(message);
    }
}
