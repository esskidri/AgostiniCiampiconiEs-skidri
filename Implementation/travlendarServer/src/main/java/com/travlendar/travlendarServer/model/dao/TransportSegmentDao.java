package com.travlendar.travlendarServer.model.dao;

import com.travlendar.travlendarServer.model.domain.TransportSegment;
import com.travlendar.travlendarServer.model.domain.TransportSegmentId;
import org.springframework.data.repository.CrudRepository;

public interface TransportSegmentDao extends CrudRepository<TransportSegment,TransportSegmentId> {

}
