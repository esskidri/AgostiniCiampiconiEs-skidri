package com.travlendar.travlendarServer.extra;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonOperation {

    public static String toJson(Object obj){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.S").setPrettyPrinting().create();
        return gson.toJson(obj);
    }


}
