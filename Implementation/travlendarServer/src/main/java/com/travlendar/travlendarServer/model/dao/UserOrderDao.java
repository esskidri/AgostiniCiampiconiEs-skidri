package com.travlendar.travlendarServer.model.dao;

import com.travlendar.travlendarServer.model.UserOrder;
import org.springframework.data.repository.CrudRepository;

public interface UserOrderDao extends CrudRepository<UserOrder,Long> {
}
