package com.travlendar.travlendarServer.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "transport_solution")
public class TransportSolution implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name="event_id_1")
    private Event event1;

    @Id
    @OneToOne
    @JoinColumn(name="event_id_2")
    private Event event2;

    @OneToMany(mappedBy = "transportSolution")
    private List<TransportSegment> transportSegments;
}
