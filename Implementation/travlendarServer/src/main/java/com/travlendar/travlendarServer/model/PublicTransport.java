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


    @OneToMany(mappedBy = "publicTransport")
    private List<UserPublicTransport> UserpublicTransports;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gree_id")
    private Green green;


    @OneToMany(mappedBy = "publicTransport")
    private List<PublicTransport> publicTransport;

    @OneToMany(mappedBy = "privateTransport")
    private List<PrivateTransport> privateTransport;

    @OneToMany(mappedBy = "publicTransport")
    private List<TransportSegment> transportSegments;

    public PublicTransport(String name, List<UserPublicTransport> userpublicTransports, Green green, List<PublicTransport> publicTransport, List<PrivateTransport> privateTransport, List<TransportSegment> transportSegments) {
        this.name = name;
        UserpublicTransports = userpublicTransports;
        this.green = green;
        this.publicTransport = publicTransport;
        this.privateTransport = privateTransport;
        this.transportSegments = transportSegments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserPublicTransport> getUserpublicTransports() {
        return UserpublicTransports;
    }

    public void setUserpublicTransports(List<UserPublicTransport> userpublicTransports) {
        UserpublicTransports = userpublicTransports;
    }

    public Green getGreen() {
        return green;
    }

    public void setGreen(Green green) {
        this.green = green;
    }

    public List<PublicTransport> getPublicTransport() {
        return publicTransport;
    }

    public void setPublicTransport(List<PublicTransport> publicTransport) {
        this.publicTransport = publicTransport;
    }

    public List<PrivateTransport> getPrivateTransport() {
        return privateTransport;
    }

    public void setPrivateTransport(List<PrivateTransport> privateTransport) {
        this.privateTransport = privateTransport;
    }

    public List<TransportSegment> getTransportSegments() {
        return transportSegments;
    }

    public void setTransportSegments(List<TransportSegment> transportSegments) {
        this.transportSegments = transportSegments;
    }
}


