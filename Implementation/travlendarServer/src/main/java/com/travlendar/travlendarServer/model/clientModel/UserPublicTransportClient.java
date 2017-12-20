package com.travlendar.travlendarServer.model.clientModel;

import java.io.Serializable;

public class UserPublicTransportClient implements Serializable{
    private String username;
    private PublicTransportClient publicTransportclient;

    public UserPublicTransportClient(String username, PublicTransportClient publicTransportclient) {
        this.username = username;
        this.publicTransportclient = publicTransportclient;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PublicTransportClient getPublicTransportclient() {
        return publicTransportclient;
    }

    public void setPublicTransportclient(PublicTransportClient publicTransportclient) {
        this.publicTransportclient = publicTransportclient;
    }
}
