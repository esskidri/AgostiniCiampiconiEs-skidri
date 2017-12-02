package com.travlendar.travlendarServer.model;

import javax.persistence.*;


@Entity
@Table(name = "Green")
public class green {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String level;
    private String description;

}
