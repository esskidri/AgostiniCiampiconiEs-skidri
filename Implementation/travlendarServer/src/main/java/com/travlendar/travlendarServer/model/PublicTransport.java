package com.travlendar.travlendarServer.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "public_transport")
public class PublicTransport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    
    @Column(name="name")
    private String name;

    @Column(name="green_id")
    private int greenId;

    @OneToMany(mappedBy = "publicTransport")
    private List<UserPublicTransport> UserpublicTransports;




}


