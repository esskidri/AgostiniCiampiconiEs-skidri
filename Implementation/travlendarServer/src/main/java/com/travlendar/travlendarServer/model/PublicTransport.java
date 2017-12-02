package com.travlendar.travlendarServer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "public_transport")
public class PublicTransport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique=true)
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "green")
    private long greenId;

    public PublicTransport(String name, long greenId) {
        this.name = name;
        this.greenId = greenId;
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

    public long getGreenId() {
        return greenId;
    }

    public void setGreenId(long greenId) {
        this.greenId = greenId;
    }
}
