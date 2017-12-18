package com.example.ago.travlendarandroidclient.serverStub;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.example.ago.travlendarandroidclient.UserSettings;
import com.example.ago.travlendarandroidclient.model.UserClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ExecutionException;

/**
 * Created by ago on 12/12/2017.
 */

public class ServerConnection {
    private final static String serverUrl = "http://192.168.1.4:8080";
    //Instantiate new instance of our class

    //Perform the doInBackground method, passing in our url


    public static String getUserDescription(Long id) {
        String result = "";
        String url = serverUrl + "/user-description/?" + "id=" + id;
        HttpGetRequest getRequest = new HttpGetRequest();
        try {
            result = getRequest.execute(url).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String fetchEvents(Long id) {
        String result = "";
        String url = serverUrl + "/fetch-events/?" + "id=" + id;
        HttpGetRequest getRequest = new HttpGetRequest();
        try {
            result = getRequest.execute(url).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void userInit(Long id, Context context) {

        String result = "";
        String url = serverUrl + "/get-user/?" + "user_id=" + id;
        HttpGetRequest getRequest = new HttpGetRequest();
        try {
            result  = getRequest.execute(url).get();
            Gson gson = new GsonBuilder().create();
            UserClient userClient= gson.fromJson(result,UserClient.class);
            UserSettings.setUserClient(userClient);
            Toast.makeText(context,"BENTORNATO : " + userClient.getFirst_name(),
                    Toast.LENGTH_SHORT).show();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
