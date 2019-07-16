package com.falanadamian.krim.schedule.exception;

public class UserNotActivatedException extends RuntimeException{

    public UserNotActivatedException() {
        super("User not activated!");
    }

    public UserNotActivatedException(String message) {
        super(message);
    }
}
