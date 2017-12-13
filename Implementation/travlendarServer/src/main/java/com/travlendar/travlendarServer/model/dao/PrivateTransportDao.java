package com.travlendar.travlendarServer.model.dao;

import com.travlendar.travlendarServer.controller.Exception.DataEntryException;
import com.travlendar.travlendarServer.model.domain.PrivateTransport;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;


public interface PrivateTransportDao extends CrudRepository<PrivateTransport, Long> {
    /**
     * save a new private transport
     * @param p private transport to save
     * @return
     * @throws Exception
     */
    default PrivateTransport customSave (PrivateTransport p) throws Exception {
        p.setName(p.getName().replaceAll("\\s+",""));
        if(p.getName()==null || p.getName().length()<1) throw new DataEntryException("invalid name");
        if(p.getDisplacement()<0) throw new DataEntryException("invalid displacement");
        save(p);
        return p;
    }

}