package com.travlendar.travlendarServer.model.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TransportSolutionId implements Serializable {

    @Column(name="event_id_1")
    private long event_id_1;

    @Column(name="event_id_2")
    private long event_id_2;

    public TransportSolutionId() {}
    public TransportSolutionId(long event_id_1, long event_id_2) {
        this.event_id_1 = event_id_1;
        this.event_id_2 = event_id_2;
    }
}
