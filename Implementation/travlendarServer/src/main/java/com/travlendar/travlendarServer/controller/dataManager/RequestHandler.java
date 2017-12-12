package com.travlendar.travlendarServer.controller.dataManager;


import com.travlendar.travlendarServer.controller.Exception.DataEntryException;
import com.travlendar.travlendarServer.model.dao.*;
import com.travlendar.travlendarServer.model.domain.Event;
import com.travlendar.travlendarServer.model.domain.PrivateTransport;
import com.travlendar.travlendarServer.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.sql.Timestamp;

import static com.travlendar.travlendarServer.extra.Converter.createTimeStampFromDate;

@Controller
@Transactional
public class RequestHandler {

    /**********DAO AUTOWIRED***********/
    @Autowired
    private UserDao userDao;
    @Autowired
    private FreetTimeDao freetTimeDao;
    @Autowired
    private PrivateTransportDao privateTransportDao;
    @Autowired
    private EventDao eventDao;



    @RequestMapping("/add-event")
    @ResponseBody
    public Response addEvent(@RequestParam("user_id") Long userId, @RequestParam("start_date") Timestamp startDate,
                             @RequestParam("end_date")Timestamp endDate,@RequestParam("pos_x") Float posX,
                             @RequestParam("pos_y") Float posY,@RequestParam("description") String description,
                             @RequestParam("name") String name,@RequestParam("end_event")Boolean endEvent) {
        Response r=new Response("");
        //fetch the user
        User u=userDao.findOne(userId);
        //create the event
        Event e=new Event(u,startDate,endDate,posX,posY,description,name,endEvent);

        try {
            eventDao.customSave(e);
        }catch(DataEntryException e1){
            r.setMessage(e1.getMessage());
        }catch(Exception e2){
            r.setMessage("fail"+e2.getMessage());
        }

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
