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

    @Column(name="green_id")
    private int greenId;

    @Column(name="license_plate")
    private String license_plate;

    @ManyToMany
    @JoinTable(name="user_private_transport",
            joinColumns={@JoinColumn(name="private_transport")},
            inverseJoinColumns={@JoinColumn(name="user_id")})
    private List<User> users;
    public List<User> getUsers(){
        return users;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "green_id")
    private Green green;
    public Green getGreen() {
        return this.green;
    }

    public PrivateTransport(){}



}
