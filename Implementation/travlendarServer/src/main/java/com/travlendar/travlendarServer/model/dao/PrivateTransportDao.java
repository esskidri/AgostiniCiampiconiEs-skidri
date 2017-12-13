package com.travlendar.travlendarServer.model.dao;

import com.travlendar.travlendarServer.model.domain.PrivateTransport;
import com.travlendar.travlendarServer.model.domain.PublicTransport;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface PrivateTransportDao extends CrudRepository<PrivateTransport, Long> {

    public PublicTransport findById(long id);
}