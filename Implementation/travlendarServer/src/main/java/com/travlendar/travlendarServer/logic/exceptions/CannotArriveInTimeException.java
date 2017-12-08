package com.travlendar.travlendarServer.logic.exceptions;

public class CannotArriveInTimeException extends Throwable {
    public CannotArriveInTimeException() {
    }

    public CannotArriveInTimeException(String message) {
        super(message);
    }
}
