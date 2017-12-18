package com.travlendar.travlendarServer.model.dao;

import com.travlendar.travlendarServer.model.enumModel.EnumGreenLevel;
import com.travlendar.travlendarServer.model.domain.Green;
import org.springframework.data.repository.CrudRepository;

public interface GreenDao extends CrudRepository<Green,Long> {
    public Green findByLevel(EnumGreenLevel greenLevel);
}
