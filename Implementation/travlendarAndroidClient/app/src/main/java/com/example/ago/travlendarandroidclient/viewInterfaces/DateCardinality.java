package com.example.ago.travlendarandroidclient.viewInterfaces;

import java.util.Calendar;

/**
 * Created by ago on 18/12/2017.
 */

public class DateCardinality {
    private Calendar calendar;
    private int cardinality;

    public DateCardinality(Calendar calendar, int i) {
        this.calendar=calendar;
        this.cardinality=i;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public int getCardinality() {
        return cardinality;
    }

    public void setCardinality(int cardinality) {
        this.cardinality = cardinality;
    }

    public void incCardinality() {
        this.cardinality++;
    }
}
