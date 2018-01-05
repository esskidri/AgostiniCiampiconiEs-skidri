package com.travlendar.travlendarServer.model.domain;

import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.model.enumModel.EnumGreenLevel;
import com.travlendar.travlendarServer.model.enumModel.MeanType;
import com.travlendar.travlendarServer.model.clientModel.PublicTransportClient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "public_transport")
public class PublicTransport extends AbstractEntity implements MeanOfTransportLogic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "green_id")
    private Green green;

    @ManyToMany
    @JoinTable(name="user_public_transport",
            joinColumns={@JoinColumn(name="public_transport_id")},
            inverseJoinColumns={@JoinColumn(name="user_id")})
    private List<User> users;


    @OneToMany(mappedBy = "publicTransport")
    private List<TransportSegment> transportSegments;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private MeanType type;

    public PublicTransport(){}



    public PublicTransport(User user, Green greenLevel) {
        this.users=new ArrayList<>();
        this.users.add(user);
        this.type = MeanType.BUS;
        this.green=greenLevel;
        this.name="ATM BUS";
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User u){
        this.users.add(u);
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

    public MeanType getType() {
        return type;
    }

    public void setType(MeanType type) {
        this.type = type;
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
        return false;
    }

    public PublicTransportClient getPublicTransportClient(){
        PublicTransportClient publicTransportClient = new PublicTransportClient(id, name , type);
        return publicTransportClient;
    }
}


