package com.travlendar.travlendarServer.model.dao;


import com.travlendar.travlendarServer.controller.Exception.DataEntryException;
import com.travlendar.travlendarServer.model.domain.User;
import com.travlendar.travlendarServer.model.domain.UserOrder;
import org.springframework.data.repository.CrudRepository;



public interface UserOrderDao extends CrudRepository<UserOrder,Long> {

    default UserOrder customSave (User u, UserOrder ur) throws Exception {
        /**
         * check if there is only one kind of transport in the relative order
         */
        /*if((ur.getPrivateTransport()!= null && ur.getPublicTransport() !=null )
            || ur.getPrivateTransport()== null && ur.getPublicTransport() ==null) throw new DataEntryException("invalid order");
        /**
         * check if there order is continuous
         */
        return save(ur);
    }

}
