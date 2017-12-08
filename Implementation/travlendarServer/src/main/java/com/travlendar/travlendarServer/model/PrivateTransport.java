package com.travlendar.travlendarServer.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "private_transport")
public class PrivateTransport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    @Column(name="displacement")
    private int displacement;


    @Column(name="license_plate")
    private String license_plate;

    @ManyToMany
    @JoinTable(name="user_private_transport",
            joinColumns={@JoinColumn(name="private_transport")},
            inverseJoinColumns={@JoinColumn(name="user_id")})
    private List<User> users;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "green_id")
    private Green green;

    @OneToMany(mappedBy = "privateTransport")
    private List<TransportSegment> transportSegments;

    public PrivateTransport(){}

    public PrivateTransport(String name, String type, int displacement, String license_plate, List<User> users, Green green, List<TransportSegment> transportSegments) {
        this.name = name;
        this.type = type;
        this.displacement = displacement;
        this.license_plate = license_plate;
        this.users = users;
        this.green = green;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDisplacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Green getGreen() {
        return green;
    }

    public void setGreen(Green green) {
        this.green = green;
    }

    public List<TransportSegment> getTransportSegments() {
        return transportSegments;
    }

    public void setTransportSegments(List<TransportSegment> transportSegments) {
        this.transportSegments = transportSegments;
    }
}
