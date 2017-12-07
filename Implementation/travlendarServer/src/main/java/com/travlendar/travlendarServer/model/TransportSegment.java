package com.travlendar.travlendarServer.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "transport_segment")
public class TransportSegment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "num_order")
    private long numOrder;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "transport_solution_event_1", referencedColumnName = "event_id_1"),
            @JoinColumn(name = "transport_solution_event_2",  referencedColumnName = "event_id_2")
    })
    private TransportSolution transportSolution;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="private_transport_id")
    private PrivateTransport privateTransport;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="public_transport_id")
    private PublicTransport publicTransport;

    @Column(name="position_a_x")
    private Float positionAX;

    @Column(name="position_a_y")
    private Float positionAY;

    @Column(name="position_b_x")
    private Float positionBX;

    @Column(name="position_b_y")
    private Float positionBY;

}
