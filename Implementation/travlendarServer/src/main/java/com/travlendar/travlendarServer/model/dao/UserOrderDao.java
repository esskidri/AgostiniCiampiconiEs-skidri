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
        if((ur.getPrivateTransport()!= null && ur.getPublicTransport() !=null )
            || ur.getPrivateTransport()== null && ur.getPublicTransport() ==null) throw new DataEntryException("invalid order");
        /**
         * check if there order is continuous
         */
        int last = u.getUserOrders().size() - 1;
        if(u.getUserOrders().size() > 1) {
            if (u.getUserOrders().get(last).getOrder() - 1 != ur.getOrder())
                throw new DataEntryException("invalid sequence order");
        }
        save(ur);
        return ur;
    }

}
