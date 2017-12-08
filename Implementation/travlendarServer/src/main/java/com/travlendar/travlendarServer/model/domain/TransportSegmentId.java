package com.travlendar.travlendarServer.model.domain;

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

    public long getNum_order() {
        return num_order;
    }

    public void setNum_order(long num_order) {
        this.num_order = num_order;
    }

    public long getTransport_solution_event_id_1() {
        return transport_solution_event_id_1;
    }

    public void setTransport_solution_event_id_1(long transport_solution_event_id_1) {
        this.transport_solution_event_id_1 = transport_solution_event_id_1;
    }

    public long getTransport_solution_event_id_2() {
        return transport_solution_event_id_2;
    }

    public void setTransport_solution_event_id_2(long transport_solution_event_id_2) {
        this.transport_solution_event_id_2 = transport_solution_event_id_2;
    }

    public TransportSegmentId(long num_order, long transport_solution_event_id_1, long transport_solution_event_id_2) {
        this.num_order = num_order;
        this.transport_solution_event_id_1 = transport_solution_event_id_1;
        this.transport_solution_event_id_2 = transport_solution_event_id_2;
    }
}
