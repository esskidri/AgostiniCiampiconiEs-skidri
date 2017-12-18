package com.example.ago.travlendarandroidclient.model;


import com.example.ago.travlendarandroidclient.model.enumModel.MeanType;

import java.io.Serializable;

public class PublicTransportClient implements Serializable {
    private Long id;
    private String name;
    private MeanType type;

    public PublicTransportClient(Long id, String name, MeanType type) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public MeanType getType() {
        return type;
    }

    public void setType(MeanType type) {
        this.type = type;
    }
}
