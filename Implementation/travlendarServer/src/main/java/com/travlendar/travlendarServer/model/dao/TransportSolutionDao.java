package com.travlendar.travlendarServer.model.dao;

import com.travlendar.travlendarServer.model.domain.TransportSolution;
import com.travlendar.travlendarServer.model.domain.TransportSolutionId;
import org.springframework.data.repository.CrudRepository;

public interface TransportSolutionDao extends CrudRepository<TransportSolution,TransportSolutionId> {
}
