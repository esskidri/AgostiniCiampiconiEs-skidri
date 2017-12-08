package com.travlendar.travlendarServer.model.dao;

import com.travlendar.travlendarServer.model.domain.FreeTime;
import org.springframework.data.repository.CrudRepository;

public interface FreetTimeDao extends CrudRepository<FreeTime, Long> {
}
