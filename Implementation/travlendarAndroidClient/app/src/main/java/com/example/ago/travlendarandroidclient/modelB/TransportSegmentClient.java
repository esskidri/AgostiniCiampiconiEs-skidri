package com.example.ago.travlendarandroidclient.modelB;

/**
 * Created by ago on 16/12/2017.
 */

public class TransportSegmentClient implements StepperTransportSegment {
    private int num;
    private String name;
    private String AdressA;
    private String AdressB;
    private String distance;
    private String cost;
    private String mean;
    private int duration;

    public void setNum(int num) {
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setAdressA(String adressA) {
        AdressA = adressA;
    }


    public void setAdressB(String adressB) {
        AdressB = adressB;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAdressA() {
        return this.AdressA;
    }

    @Override
    public String getAdressB() {
        return this.AdressB;
    }

    @Override
    public String distance() {
        return distance;
    }

    @Override
    public String cost() {
        return cost;
    }

    @Override
    public String mean() {
        return mean;
    }

    @Override
    public int duration() {
        return this.duration;
    }

    public TransportSegmentClient(int num, String name, String adressA, String adressB, String distance, String cost, String mean, int Duration) {
        this.num = num;
        this.name = name;
        AdressA = adressA;
        AdressB = adressB;
        this.distance = distance;
        this.cost = cost;
        this.mean = mean;
        this.duration = Duration;
    }
}
