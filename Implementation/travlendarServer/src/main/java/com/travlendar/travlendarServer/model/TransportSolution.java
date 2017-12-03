package com.travlendar.travlendarServer.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transport_solution")
public class TransportSolution implements Serializable {

    @Id
    @Column(name = "event_id_1")
    private long eventId1;
    @Id
    @Column(name = "event_id_2")
    private long eventId2;
    @OneToOne(optional = false)
    @JoinColumn(name= "event_id_1",referencedColumnName = "id")
    private Event event1;
    @OneToOne(optional = false)
    @JoinColumn(name= "event_id_2",referencedColumnName = "id")
    private Event event2;

    public TransportSolution(long eventId1, long eventId2) {
        this.eventId1 = eventId1;
        this.eventId2 = eventId2;
    }


}
