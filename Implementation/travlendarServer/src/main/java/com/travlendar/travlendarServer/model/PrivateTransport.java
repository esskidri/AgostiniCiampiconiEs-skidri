package com.travlendar.travlendarServer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "private_transport")
public class PrivateTransport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String type; // dovrebbe essere ENUM
    @NotNull
    private int displacement;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "green")
    private long greenId;
    @Column(unique=true)
    private String licensePlate;

    public PrivateTransport(String type, int displacement, long greenId, String licensePlate) {
        this.type = type;
        this.displacement = displacement;
        this.greenId = greenId;
        this.licensePlate = licensePlate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getGreenId() {
        return greenId;
    }

    public void setGreenId(long greenId) {
        this.greenId = greenId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
