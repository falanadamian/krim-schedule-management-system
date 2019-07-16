package com.falanadamian.krim.schedule.exception;

public class InvalidPasswordException extends RuntimeException{

    public InvalidPasswordException() {
        super("Invalid password");
    }
}
