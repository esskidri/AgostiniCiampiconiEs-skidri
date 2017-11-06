package com.example.travlendar.model;

/**
 * Created by Lorenzo on 11/6/2017.
 */

public class User {
    private int id;
    private String eMail;
    private Calendar calendar;

    public User(int id, String eMail, Calendar calendar) {
        this.id = id;
        this.eMail = eMail;
        this.calendar = calendar;
    }
}
