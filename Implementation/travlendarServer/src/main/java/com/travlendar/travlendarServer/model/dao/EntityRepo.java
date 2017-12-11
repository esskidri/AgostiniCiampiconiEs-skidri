package com.travlendar.travlendarServer.model.dao;

import com.travlendar.travlendarServer.controller.Exception.UserException;
import com.travlendar.travlendarServer.model.domain.AbstractEntity;
import com.travlendar.travlendarServer.model.domain.Event;

public interface EntityRepo {
    <S extends AbstractEntity> S save(S entity) throws UserException;
}
