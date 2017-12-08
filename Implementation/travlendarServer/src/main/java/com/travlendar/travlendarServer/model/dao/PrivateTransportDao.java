package com.travlendar.travlendarServer.model.dao;

import com.travlendar.travlendarServer.model.PrivateTransport;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface PrivateTransportDao extends CrudRepository<PrivateTransport, Long> {

    public PrivateTransport findById(long id);
}