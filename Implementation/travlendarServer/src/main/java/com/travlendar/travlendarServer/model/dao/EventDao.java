package com.travlendar.travlendarServer.model.dao;

import com.travlendar.travlendarServer.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventDao extends CrudRepository<Event, Long> {
}
