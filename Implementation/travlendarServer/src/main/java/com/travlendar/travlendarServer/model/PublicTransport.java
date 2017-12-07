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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gree_id")
    private Green green;
    public Green getGreen() {
        return this.green;
    }

    @OneToMany(mappedBy = "publicTransport")
    private List<PublicTransport> publicTransport;

    @OneToMany(mappedBy = "privateTransport")
    private List<PrivateTransport> privateTransport;




}


