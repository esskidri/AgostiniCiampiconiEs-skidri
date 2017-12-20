package com.travlendar.travlendarServer.logic.exceptions;

public class TimeViolationException extends Exception {
    public TimeViolationException() {
    }

    public TimeViolationException(String message) {
        super(message);
    }
}
