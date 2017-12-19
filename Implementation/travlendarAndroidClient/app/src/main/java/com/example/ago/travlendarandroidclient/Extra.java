package com.example.ago.travlendarandroidclient;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by ago on 18/12/2017.
 */

public class Extra {

    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
