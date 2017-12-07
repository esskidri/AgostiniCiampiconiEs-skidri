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
    public User getUser() {
        return this.user;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "public_transport_id")
    private PublicTransport publicTransport;
    public PublicTransport getPublicTransport() {
        return this.publicTransport;
    }

    public UserPublicTransport(){}

}