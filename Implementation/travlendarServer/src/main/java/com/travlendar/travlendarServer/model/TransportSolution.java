package com.travlendar.travlendarServer.model;

import javax.persistence.*;

@Entity
@Table(name = "transport_solution")
public class TransportSolution {

    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event")
    private long event1;
    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event")
    private long event2;

    public TransportSolution(long event1, long event2) {
        this.event1 = event1;
        this.event2 = event2;
    }

    public long getEvent1() {
        return event1;
    }

    public void setEvent1(long event1) {
        this.event1 = event1;
    }

    public long getEvent2() {
        return event2;
    }

    public void setEvent2(long event2) {
        this.event2 = event2;
    }
}
