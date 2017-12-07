package com.travlendar.travlendarServer.model;


import javax.persistence.*;


@Entity
@Table(name = "user_order")
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "order")
    private int order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;
    public User getUser() {
        return this.user;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "public_transport_id")
    private PublicTransport publicTransport;
    public PublicTransport getPublicTransport() {
        return this.publicTransport;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "private_transport_id")
    private PrivateTransport privateTransport;
    public PrivateTransport getPrivateTransport() {
        return this.privateTransport;
    }
}
