package com.falanadamian.krim.schedule.exception;

public class EmailAlreadyInUseException extends RuntimeException{

    public EmailAlreadyInUseException() {
        super("Email already in use.");
    }

    public EmailAlreadyInUseException(String email) {
        super("Email: " + email + "arleady in use");
    }
}
