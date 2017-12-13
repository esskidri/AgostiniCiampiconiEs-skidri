package com.travlendar.travlendarServer.model.dao;


import com.travlendar.travlendarServer.model.domain.PublicTransport;
import org.springframework.data.repository.CrudRepository;

public interface PublicTransportDao extends CrudRepository<PublicTransport, Long> {
}
