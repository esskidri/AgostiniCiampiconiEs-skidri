package com.example.ago.travlendarandroidclient;

/**
 * Created by ago on 15/12/2017.
 */


public class UserSettings {
    private static long user_id=6;
    private static String fName="Andrea";
    private static String lName="Agostini";

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
}