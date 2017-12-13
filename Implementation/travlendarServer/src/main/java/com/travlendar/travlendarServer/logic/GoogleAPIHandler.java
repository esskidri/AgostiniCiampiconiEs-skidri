package com.travlendar.travlendarServer.logic;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travlendar.travlendarServer.logic.util.GoogleResponseMappedObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static java.lang.Thread.sleep;

public class GoogleAPIHandler {

    private GoogleAPIHandler() {
    }

    private static final String PROTOCOL = "https:";
    private static final String GOOGLE_URL = "//maps.googleapis.com/maps/api/directions/";
    private static final String RESPONSE_TYPE = "json";
    private static final String SEPARATOR = "&";
    private static final String EQUAL = "=";
    private static final String ORIGIN = "origin";
    private static final String DESTINATION = "destination";
    private static final String MEANOFTRANSPORT = "mode";
    private static final String ARRIVALTIME = "arrival_time";
    private static final String KEY = "key";
    private static final String API_KEY = "AIzaSyClU3xiXoQgD3E_VrESZB8s3nxxm0gecVc";

    public static void main(String[] args) {
        //TODO Remove main()
        System.out.println(httpsRequest(constructHttpsUrl("Rome","Milan", "transit", "1391374800")));

        //Testing https request and Google JSON to POJO mapping
        GoogleResponseMappedObject googleResponseMappedObject;
        googleResponseMappedObject = fromJsonToObject(httpsRequest(constructHttpsUrl("Via+Celeste+Clericetti,+32,+20133+Milano+MI","Via+delle+Rimembranze,+20068+Peschiera+Borromeo+MI", "transit", "1513342800000")));

    }

    public static GoogleResponseMappedObject askGoogle(String origin, String destination, String meanOfTransport, String arrivalTime){
        return fromJsonToObject(httpsRequest(constructHttpsUrl(origin, destination, meanOfTransport, arrivalTime)));
    }

    private static GoogleResponseMappedObject fromJsonToObject(String jsonString){
        GoogleResponseMappedObject googleResponseMappedObject = new GoogleResponseMappedObject();
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try{
            googleResponseMappedObject = mapper.readValue(jsonString, GoogleResponseMappedObject.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googleResponseMappedObject;
    }

    /**
     * This method request to Google
     * @param urlString
     * @return
     */
    private static String httpsRequest(String urlString){
        StringBuilder jsonString = new StringBuilder();
        try{
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputString;
            while ((inputString = input.readLine()) != null)
                jsonString.append(inputString).append("\n");
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString.toString();
    }

    /**
     * This method construct the URL for the HTTPS request by the instructions on the Google API Documentation
     * @param origin
     * @param destination
     * @param meanOfTransport
     * @param arrivalTime
     * @return
     */
    private static String constructHttpsUrl(String origin, String destination, String meanOfTransport, String arrivalTime){
        return PROTOCOL + GOOGLE_URL + RESPONSE_TYPE +
                "?" + ORIGIN + EQUAL + origin + SEPARATOR + DESTINATION
                + EQUAL + destination + SEPARATOR + MEANOFTRANSPORT + EQUAL +
                meanOfTransport + SEPARATOR + ARRIVALTIME + EQUAL + arrivalTime +
                SEPARATOR + KEY + EQUAL + API_KEY;
    }
}
