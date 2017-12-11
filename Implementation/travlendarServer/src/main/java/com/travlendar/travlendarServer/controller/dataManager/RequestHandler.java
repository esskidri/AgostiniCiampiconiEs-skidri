package com.travlendar.travlendarServer.controller.dataManager;


import com.travlendar.travlendarServer.controller.Exception.UserException;
import com.travlendar.travlendarServer.model.domain.Event;
import com.travlendar.travlendarServer.model.domain.PrivateTransport;
import com.travlendar.travlendarServer.model.domain.User;
import com.travlendar.travlendarServer.model.dao.FreetTimeDao;
import com.travlendar.travlendarServer.model.dao.PrivateTransportDao;
import com.travlendar.travlendarServer.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;

@Controller
public class RequestHandler {

    /**********DAO AUTOWIRED***********/
    @Autowired
    private UserDao userDao;
    @Autowired
    private FreetTimeDao freetTimeDao;
    @Autowired
    private PrivateTransportDao privateTransportDao;



    @RequestMapping("/add-event")
    @ResponseBody
    public Response addEvent(@RequestParam("user_id") Long userId, @RequestParam("start_date") Timestamp startDate,
                             @RequestParam("end_date")Timestamp endDate,@RequestParam("pos_x") Float posX,
                             @RequestParam("pos_y") Float posY) {



        Response r=new Response("");
        return r;
    }

    @RequestMapping("/delete-event")
    @ResponseBody
    public Response deleteEvent() {

        Response r=new Response("");
        return r;
    }



    @RequestMapping("/add-private-transport")
    @ResponseBody
    public Response addPrivateTransport(@RequestParam("user_id") Long id,@RequestParam("name") String name,@RequestParam("type") String type,
                                        @RequestParam("displacement") int displacement,@RequestParam("license") String licensePlate){
        User user=userDao.findOne(id);
        PrivateTransport privateTransport=new PrivateTransport();
        Response r=new Response("");
        return r;
    }





}
