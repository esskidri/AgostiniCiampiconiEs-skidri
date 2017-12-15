package com.example.ago.travlendarandroidclient.serverStub;

import com.example.ago.travlendarandroidclient.serverStub.HttpGetRequest;

import java.util.concurrent.ExecutionException;

/**
 * Created by ago on 12/12/2017.
 */

public class ServerConnection {
    private final static String serverUrl="http://10.0.2.2:8080";
    //Instantiate new instance of our class

    //Perform the doInBackground method, passing in our url


    public static String getUserDescription(Long id){
        String result="";
        String url=serverUrl+"/user-description/?"+"id="+id;
        HttpGetRequest getRequest = new HttpGetRequest();
        try {
            result = getRequest.execute(url).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

}
