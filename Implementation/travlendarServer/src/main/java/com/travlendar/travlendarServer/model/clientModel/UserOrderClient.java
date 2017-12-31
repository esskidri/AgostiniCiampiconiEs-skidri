package com.travlendar.travlendarServer.model.clientModel;

import java.io.Serializable;

public class UserOrderClient implements Serializable {
    private Long id;
    private int order;
    private PublicTransportClient publicTransport;
    private PrivateTransportClient privateTransport;

    public UserOrderClient(Long id, int order, PublicTransportClient publicTransport, PrivateTransportClient privateTransport) {
        this.id = id;
        this.order = order;
        this.publicTransport = publicTransport;
        this.privateTransport = privateTransport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public PublicTransportClient getPublicTransport() {
        return publicTransport;
    }

    public void setPublicTransport(PublicTransportClient publicTransport) {
        this.publicTransport = publicTransport;
    }

    public PrivateTransportClient getPrivateTransport() {
        return privateTransport;
    }

    public void setPrivateTransport(PrivateTransportClient privateTransport) {
        this.privateTransport = privateTransport;
    }

    public boolean isPrivate(){
        if (this.publicTransport==null) return true;
        else return false;
    }
}
