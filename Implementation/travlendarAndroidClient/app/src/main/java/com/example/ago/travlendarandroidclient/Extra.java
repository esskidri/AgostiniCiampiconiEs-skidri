package com.example.ago.travlendarandroidclient;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by ago on 18/12/2017.
 */

public class Extra {

    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    //da testare tronca all' ora in cui è
    //DA TESTARE
    public static String fromCalendarTo24Hour(Calendar c){
        if(c.get(Calendar.AM_PM)==Calendar.AM) {
            if (c.get(Calendar.HOUR_OF_DAY) < 10) {
                return "0" + c.get(Calendar.HOUR_OF_DAY) + ":00";
            } else {
                return c.get(Calendar.HOUR_OF_DAY) + ":00";
            }
        }else{
            return (c.get(Calendar.HOUR_OF_DAY))+":00";
        }
    }
}
