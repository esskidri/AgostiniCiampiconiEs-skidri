package com.example.travlendar.model.Exceptions;

import java.io.IOException;

/**
 * Created by Lorenzo on 11/6/2017.
 */

public class OverlappingException extends IOException{
    public OverlappingException(String s) {
        super(s);
    }
}
