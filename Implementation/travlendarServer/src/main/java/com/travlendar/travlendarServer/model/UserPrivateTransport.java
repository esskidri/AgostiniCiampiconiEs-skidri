package com.travlendar.travlendarServer.model;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Table(name = "user_private_transport")
public class UserPrivateTransport {

    @PrimaryKeyJoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private long userId;
    @PrimaryKeyJoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "private_transport")
    private long privateTransportId;

    public UserPrivateTransport(long userId, long privateTransportId) {
        this.userId = userId;
        this.privateTransportId = privateTransportId;
    }

    public long getUserId() {
        return userId;
    }


    public long getPrivateTraansportId() {
        return privateTransportId;
    }

    public void setPrivateTraansportId(long privateTraansportId) {
        this.privateTransportId = privateTraansportId;
    }


}

