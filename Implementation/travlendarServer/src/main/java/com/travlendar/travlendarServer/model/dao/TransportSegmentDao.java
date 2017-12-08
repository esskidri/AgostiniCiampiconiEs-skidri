package com.travlendar.travlendarServer.model.dao;

import com.travlendar.travlendarServer.model.TransportSegment;
import com.travlendar.travlendarServer.model.TransportSegmentId;
import org.springframework.data.repository.CrudRepository;

public interface TransportSegmentDao extends CrudRepository<TransportSegment,TransportSegmentId> {

}
