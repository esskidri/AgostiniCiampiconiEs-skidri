package com.travlendar.travlendarServer.extra;

import com.travlendar.travlendarServer.controller.Exception.DataEntryException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

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

    public static void coordinatesValidation(float posX,float posY) throws DataEntryException{
        if(posX<-90 ||posX>90 || posY<-180 || posY>180){
            //todo perfezionare con controllo vero
            throw new DataEntryException("invalid Adress");
        }
    }

    public static String getSecondsFromTimeStamp(Timestamp timestamp){
        return ((Long) (timestamp.getTime()/1000)).toString();
    }

}
