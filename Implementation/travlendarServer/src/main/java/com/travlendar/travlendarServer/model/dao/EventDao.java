package com.travlendar.travlendarServer.model.dao;

import com.travlendar.travlendarServer.controller.Exception.DataEntryException;
import com.travlendar.travlendarServer.model.domain.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventDao extends CrudRepository<Event, Long> {

    default Event customSave (Event e) throws Exception {
        e.setName(e.getName().replaceAll("\\s+",""));
        if(e.getStartDate().compareTo(e.getEndDate())>0) throw new DataEntryException("EndDate before StartDate");
        if(e.getName()==null || e.getName().length()<1) throw new DataEntryException("invalid name");
        save(e);
        return e;
    }


}
