package com.travlendar.travlendarServer.model.dao;

import com.travlendar.travlendarServer.model.EnumGreenLevel;
import com.travlendar.travlendarServer.model.MeanType;
import com.travlendar.travlendarServer.model.domain.Green;
import org.springframework.data.repository.CrudRepository;

public interface GreenDao extends CrudRepository<Green,Long> {
    public Green findByLevel(EnumGreenLevel greenLevel);
}
