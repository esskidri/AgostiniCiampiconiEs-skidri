package com.travlendar.travlendarServer.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_public_transport")
public class UserPublicTransport implements Serializable{
    @Id
    @Column(name= "id")
    private long id;
    @Id
    @Column(name = "username")
    private String username;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "public_transport_id")
    private PublicTransport publicTransport;


    public UserPublicTransport(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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