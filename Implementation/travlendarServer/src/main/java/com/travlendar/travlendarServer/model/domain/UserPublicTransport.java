package com.travlendar.travlendarServer.model.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_public_transport")
public class UserPublicTransport implements Serializable{

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

}