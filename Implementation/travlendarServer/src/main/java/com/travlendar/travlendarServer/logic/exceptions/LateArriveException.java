package com.travlendar.travlendarServer.logic.exceptions;

/**
 * Created by Lorenzo on 20/12/2017.
 */
public class LateArriveException extends Exception {
    public LateArriveException() {
    }

    public LateArriveException(String message) {
        super(message);
    }
}
