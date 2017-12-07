package com.travlendar.travlendarServer.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "event")
public class Event implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name="end_date")
    private Timestamp endDate;

    @Column(name = "pos_x")
    private float posX;

    @Column(name = "pos_y")
    private float posY;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "end_event")
    private  boolean endEvent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="event1")
    private List<TransportSolution> transportSolutions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="event2")
    private List<TransportSolution> transportSolutions2;


}
