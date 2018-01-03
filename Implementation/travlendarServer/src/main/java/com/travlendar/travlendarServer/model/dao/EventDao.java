package com.travlendar.travlendarServer.model.dao;

import com.travlendar.travlendarServer.controller.Exception.DataEntryException;
import com.travlendar.travlendarServer.model.domain.Event;
import com.travlendar.travlendarServer.model.domain.User;
import org.springframework.data.repository.CrudRepository;

import static com.travlendar.travlendarServer.extra.Tools.coordinatesValidation;

public interface EventDao extends CrudRepository<Event, Long> {

    /**
     * check validity and save
     * @param e the event to save
     * @return the event just saved
     * @throws Exception
     */
    default void customSave (Event e) throws Exception {
        e.setName(e.getName().replaceAll("\\s+",""));
        if(e.getStartDate().compareTo(e.getEndDate())>0) throw new DataEntryException("EndDate before StartDate");
        if(e.getName()==null || e.getName().length()<1) throw new DataEntryException("invalid name");
        if(e.getPosX() == 0 && e.getPosY() == 0) throw new DataEntryException("Unable to recognize location, try with another address");
        coordinatesValidation(e.getPosX(),e.getPosY());
        save(e);
    }

    /**
     * check validity and delete
     * @param e the event to save
     * @param u the user that owns the event
     * @throws Exception
     */
    default void customDelete(Event e, User u) throws Exception{
        if(u==null)throw new DataEntryException("user not found in DB");
        if(e==null)throw new DataEntryException("event not found in DB");
        if(e.getUser().getId()!=u.getId()) throw new DataEntryException("user doesn't own the event");
        delete(e);
    }

    /**
     * check validity and update
     * @param e the event to save
     * @param u the user that owns the event
     * @throws Exception
     */
    default Event customUpdate(Event e,User u) throws Exception{
        if(e.getUser().getId()!=u.getId()) throw new DataEntryException("user doesn't own the event");
        customSave(e);
        return e;
    }


}
