package com.travlendar.travlendarServer.extra;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converter {

    public static Timestamp createTimeStampFromDate(String source){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy/hh:mm");

        Date date = null;
        try {
            date = dateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = date.getTime();
        return new Timestamp(time);
    }
}
