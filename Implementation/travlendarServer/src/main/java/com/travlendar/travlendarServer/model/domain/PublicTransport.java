package com.travlendar.travlendarServer.model.domain;

import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.model.EnumGreenLevel;
import com.travlendar.travlendarServer.model.MeanType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "public_transport")
public class PublicTransport extends AbstractEntity implements MeanOfTransportLogic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

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

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private MeanType type;

    private PublicTransport(){}

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
}


