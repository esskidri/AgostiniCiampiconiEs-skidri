package com.travlendar.travlendarServer.model;

import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface PrivateTransportDao extends CrudRepository<PrivateTransport, Long> {

}