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


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "public_transport_id")
    private PublicTransport publicTransport;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "private_transport_id")
    private PrivateTransport privateTransport;

    public UserOrder(int order, User user, PublicTransport publicTransport, PrivateTransport privateTransport) {
        this.order = order;
        this.user = user;
        this.publicTransport = publicTransport;
        this.privateTransport = privateTransport;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PublicTransport getPublicTransport() {
        return publicTransport;
    }

    public void setPublicTransport(PublicTransport publicTransport) {
        this.publicTransport = publicTransport;
    }

    public PrivateTransport getPrivateTransport() {
        return privateTransport;
    }

    public void setPrivateTransport(PrivateTransport privateTransport) {
        this.privateTransport = privateTransport;
    }
}
