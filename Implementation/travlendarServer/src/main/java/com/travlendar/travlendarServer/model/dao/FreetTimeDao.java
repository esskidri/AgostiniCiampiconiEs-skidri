package com.travlendar.travlendarServer.model.dao;


import com.travlendar.travlendarServer.controller.Exception.DataEntryException;
import com.travlendar.travlendarServer.model.domain.FreeTime;
import com.travlendar.travlendarServer.model.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface FreetTimeDao extends CrudRepository<FreeTime, Long> {
    default FreeTime customSave( FreeTime freeTime ) throws Exception{
        if(freeTime.getStartDate().compareTo(freeTime.getEndDate()) == 1) throw new DataEntryException("start date must be before end date");
        if(freeTime.getEndDate().getTime() - freeTime.getStartDate().getTime() < freeTime.getDuration())
            throw new DataEntryException("duration non valid");
        save(freeTime);
        return null;
    }
}
