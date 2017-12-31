package com.travlendar.travlendarServer.model.domain;


import com.travlendar.travlendarServer.model.clientModel.UserClient;
import com.travlendar.travlendarServer.model.clientModel.UserOrderClient;

import javax.persistence.*;


@Entity
@Table(name = "user_order")
public class UserOrder extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "num_order")
    private int order;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="user_id")
    private User user;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "public_transport_id")
    private PublicTransport publicTransport;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "private_transport_id")
    private PrivateTransport privateTransport;

    public UserOrder(){}

    public UserOrder(int order, User user, PublicTransport publicTransport, PrivateTransport privateTransport) {
        this.order = order;
        this.user = user;
        this.publicTransport = publicTransport;
        this.privateTransport = privateTransport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public UserOrderClient getUserOrderClient(){
        UserOrderClient userOrderClient;
        if(publicTransport != null) {
           userOrderClient = new UserOrderClient(id, order, publicTransport.getPublicTransportClient()
                    , null);
        }else{
           userOrderClient = new UserOrderClient(id, order, null
                    , privateTransport.getPrivateTransportClient());
        }
        return userOrderClient;
    }


}
