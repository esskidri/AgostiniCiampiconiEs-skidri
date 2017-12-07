package com.travlendar.travlendarServer.model.dao;

import com.travlendar.travlendarServer.model.TransportSolution;
import com.travlendar.travlendarServer.model.TransportSolutionId;
import org.springframework.data.repository.CrudRepository;

public interface TransportSolutionDao extends CrudRepository<TransportSolution,TransportSolutionId> {
}
