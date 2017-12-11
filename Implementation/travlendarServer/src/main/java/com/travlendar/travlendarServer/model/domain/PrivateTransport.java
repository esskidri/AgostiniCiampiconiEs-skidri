package com.travlendar.travlendarServer.model.domain;


import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.model.EnumGreenLevel;
import com.travlendar.travlendarServer.model.MeanType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "private_transport")
public class PrivateTransport implements MeanOfTransportLogic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private MeanType type;

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

    //distance in meters
    @Column(name="distance")
    private int distance;

    @Column(name="description")
    private String description;

    //duration in seconds
    @Column(name="duration")
    private int duration;



    public PrivateTransport(){}

    public PrivateTransport(String name, MeanType type, int displacement, String license_plate, List<User> users, Green green, List<TransportSegment> transportSegments) {
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

    public MeanType getType() {
        return type;
    }

    public void setType(MeanType type) {
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public MeanType getTypeOfTransport() {
        return type;
    }

    @Override
    public EnumGreenLevel getGreenLevel() {
        return green.getLevel();
    }

    @Override
    public boolean isPrivate() {
        return true;
    }
}
