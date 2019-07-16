package com.falanadamian.krim.schedule.exception;

public class UsernameAlreadyInUseException extends RuntimeException{

    public UsernameAlreadyInUseException() {
        super("Username already in use.");
    }

    public UsernameAlreadyInUseException(String username) {
        super("Username: " + username + "arleady in use");
    }
}
