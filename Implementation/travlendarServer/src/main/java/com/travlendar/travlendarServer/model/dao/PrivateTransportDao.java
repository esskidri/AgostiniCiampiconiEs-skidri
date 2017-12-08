package com.travlendar.travlendarServer.model.dao;

import com.travlendar.travlendarServer.model.domain.PrivateTransport;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface PrivateTransportDao extends CrudRepository<PrivateTransport, Long> {

    public PrivateTransport findById(long id);
}