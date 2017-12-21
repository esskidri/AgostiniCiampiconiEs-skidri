package com.example.ago.travlendarandroidclient;

import com.alamkanak.weekview.WeekViewEvent;
import com.example.ago.travlendarandroidclient.model.EventClient;
import com.example.ago.travlendarandroidclient.model.UserClient;
import com.example.ago.travlendarandroidclient.viewInterfaces.DateCardinality;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.ago.travlendarandroidclient.Extra.toCalendar;

/**
 * Created by ago on 15/12/2017.
 */


public class UserSettings {

    private static List<EventClient> events=new ArrayList<>();

    private static long user_id = 6;
    private static String fName = "Andrea";
    private static String lName = "Agostini";

    private static UserClient userClient;

    public static UserClient getUserClient() {
        return userClient;
    }

    public static void setUserClient(UserClient userClient) {
        UserSettings.userClient = userClient;
    }

    public static long getUser_id() {
        return user_id;
    }

    public static void setUser_id(long user_id) {
        UserSettings.user_id = user_id;
    }

    public static String getfName() {
        return fName;
    }

    public static void setfName(String fName) {
        UserSettings.fName = fName;
    }

    public static String getlName() {
        return lName;
    }

    public static void setlName(String lName) {
        UserSettings.lName = lName;
    }


    public static List<DateCardinality> getDateCardinality() {
        //todo ottimizzare complessità
        List<DateCardinality> d = new ArrayList<>();
        List<Calendar> calendars = new ArrayList<>();
        for (EventClient e : events) {
            calendars.add(toCalendar(new Date(e.getStartDate().getTime())));
        }
        for (int i = 0; i < calendars.size(); i++) {
            if(!isPresent(d,calendars.get(i).getTime())) {
                d.add(new DateCardinality(calendars.get(i), 1));
                for (int k = i + 1; k < calendars.size(); k++) {
                    if (calendars.get(i).get(Calendar.DAY_OF_MONTH) == (calendars.get(k).get(Calendar.DAY_OF_MONTH)) &&
                            calendars.get(i).get(Calendar.MONTH) == (calendars.get(k).get(Calendar.MONTH)) &&
                            calendars.get(i).get(Calendar.YEAR) == (calendars.get(k).get(Calendar.YEAR))) {
                        d.get(d.size() - 1).incCardinality();
                    }
                }
            }
        }
        return d;
    }

    public static void setEvents(List<EventClient> events) {
        UserSettings.events = events;
    }

    public static List<EventClient> getEvents() {
        return events;
    }

    private static Boolean isPresent(List<DateCardinality> dateCardinalities, java.util.Date date){

        for(DateCardinality d:dateCardinalities){
            if(d.getCalendar().get(Calendar.DAY_OF_MONTH)==date.getDay() &&
                    d.getCalendar().get(Calendar.YEAR)==date.getYear() &&
                    d.getCalendar().get(Calendar.MONTH)==date.getMonth()){
                return true;
            }
        }
        return false;
    }

    public static List<WeekViewEvent> fromEventsToWeekViewEvents(){
        List<WeekViewEvent> weekViewEvents=new ArrayList<>();
        int i=0;

        for(EventClient e: events){
            Date dateA= new Date(e.getStartDate().getTime());
            Date dateB= new Date(e.getEndDate().getTime());
            WeekViewEvent event = new WeekViewEvent((long) i, e.getName(),toCalendar(dateA),toCalendar(dateB));
            weekViewEvents.add(event);
            i++;
        }
        return weekViewEvents;
    }
}
