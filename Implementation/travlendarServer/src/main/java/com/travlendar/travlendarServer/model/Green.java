package com.travlendar.travlendarServer.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "green")
public class Green {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "level")
    private String level;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "green")
    private List<PublicTransport> publicTransports;

    @OneToMany(mappedBy = "green")
    private List<PrivateTransport> privateTransports;


    public Green(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PublicTransport> getPublicTransports() {
        return publicTransports;
    }

    public void setPublicTransports(List<PublicTransport> publicTransports) {
        this.publicTransports = publicTransports;
    }

    public List<PrivateTransport> getPrivateTransports() {
        return privateTransports;
    }

    public void setPrivateTransports(List<PrivateTransport> privateTransports) {
        this.privateTransports = privateTransports;
    }
}
