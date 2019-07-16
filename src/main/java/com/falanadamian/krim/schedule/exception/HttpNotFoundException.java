package com.falanadamian.krim.schedule.exception;

public class HttpNotFoundException extends RuntimeException{

    public HttpNotFoundException() {
        super("Not found.");
    }

    public HttpNotFoundException(String message) {
        super(message);
    }
}
