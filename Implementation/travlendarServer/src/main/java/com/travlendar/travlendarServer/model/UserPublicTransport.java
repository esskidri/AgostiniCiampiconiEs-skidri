package com.travlendar.travlendarServer.model;

import javax.persistence.*;
@Entity
@Table(name = "user_public_transport")
public class UserPublicTransport {

    @PrimaryKeyJoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private long userId;
    @PrimaryKeyJoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "public_transport")
    private long publicTransportId;
    @Column(unique=true)
    private String userName;

    public UserPublicTransport(long userId, long publicTransportId, String userName) {
        this.userId = userId;
        this.publicTransportId = publicTransportId;
        this.userName = userName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPublicTransportId() {
        return publicTransportId;
    }

    public void setPublicTransportId(long publicTransportId) {
        this.publicTransportId = publicTransportId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
