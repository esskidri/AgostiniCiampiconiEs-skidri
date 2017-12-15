package com.travlendar.travlendarServer.model.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_public_transport")
public class UserPublicTransport extends AbstractEntity implements Serializable{

    @Id
    @Column(name = "username")
    private String username;


    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "public_transport_id")
    private PublicTransport publicTransport;

    public UserPublicTransport(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PublicTransport getPublicTransport() {
        return publicTransport;
    }

    public void setPublicTransport(PublicTransport publicTransport) {
        this.publicTransport = publicTransport;
    }
}