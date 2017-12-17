package com.travlendar.travlendarServer.model.clientModel;

import com.travlendar.travlendarServer.model.MeanType;

import java.io.Serializable;

public class PrivateTransportClient implements Serializable {
    private Long id;
    private String name;
    private MeanType meanType;
    private int displacement;
    private String license_plate;

    public PrivateTransportClient(Long id, String name, MeanType meanType, int displacement, String license_plate) {
        this.id = id;
        this.name = name;
        this.meanType = meanType;
        this.displacement = displacement;
        this.license_plate = license_plate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MeanType getMeanType() {
        return meanType;
    }

    public void setMeanType(MeanType meanType) {
        this.meanType = meanType;
    }

    public int getDisplacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }
}
