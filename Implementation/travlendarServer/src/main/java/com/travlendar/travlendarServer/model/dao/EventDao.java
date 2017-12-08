package com.travlendar.travlendarServer.model.dao;

import com.travlendar.travlendarServer.model.domain.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventDao extends CrudRepository<Event, Long> {
}
