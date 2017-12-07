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

}
