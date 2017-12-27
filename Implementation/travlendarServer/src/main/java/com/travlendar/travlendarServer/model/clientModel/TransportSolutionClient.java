package com.travlendar.travlendarServer.model.clientModel;

import java.util.List;

public class TransportSolutionClient {

    private Long eventClientId1;

    private Long eventClientId2;

    private List<TransportSegmentClient> transportSegments;



    public List<TransportSegmentClient> getTransportSegments() {
        return transportSegments;
    }

    public void setTransportSegments(List<TransportSegmentClient> transportSegments) {
        this.transportSegments = transportSegments;
    }

    public Long getEventClientId1() {
        return eventClientId1;
    }

    public void setEventClientId1(Long eventClientId1) {
        this.eventClientId1 = eventClientId1;
    }

    public Long getEventClientId2() {
        return eventClientId2;
    }

    public void setEventClientId2(Long eventClientId2) {
        this.eventClientId2 = eventClientId2;
    }

    public TransportSolutionClient(Long eventClientId1, Long eventClientId2, List<TransportSegmentClient> transportSegments) {
        this.eventClientId1 = eventClientId1;
        this.eventClientId2 = eventClientId2;
        this.transportSegments = transportSegments;
    }
}
