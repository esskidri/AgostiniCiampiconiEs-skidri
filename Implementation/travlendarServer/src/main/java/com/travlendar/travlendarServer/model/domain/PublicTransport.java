package com.travlendar.travlendarServer.model.domain;

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


    @OneToMany(mappedBy = "publicTransport")
    private List<UserPublicTransport> UserpublicTransports;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "green_id")
    private Green green;


    @OneToMany(mappedBy = "publicTransport")
    private List<PublicTransport> publicTransport;

    @OneToMany(mappedBy = "publicTransport")
    private List<TransportSegment> transportSegments;

    private PublicTransport(){}


}


