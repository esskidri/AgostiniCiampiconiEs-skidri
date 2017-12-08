package com.travlendar.travlendarServer.model;

import javax.persistence.Column;
import java.io.Serializable;

public class TransportSegmentId implements Serializable{

    @Column(name="num_order")
    private long num_order;

    @Column(name="transport_solution_event_id_1")
    private long transport_solution_event_id_1;

    @Column(name="transport_solution_event_id_2")
    private long transport_solution_event_id_2;



    public TransportSegmentId(){}


}
