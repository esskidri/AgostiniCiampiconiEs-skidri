package com.travlendar.travlendarServer.controller.dataManager;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.travlendar.travlendarServer.controller.Exception.DataEntryException;
import com.travlendar.travlendarServer.logic.MainLogic;
import com.travlendar.travlendarServer.logic.modelInterface.EventLogic;
import com.travlendar.travlendarServer.logic.modelInterface.TransportSolutionLogic;
import com.travlendar.travlendarServer.model.enumModel.MeanType;
import com.travlendar.travlendarServer.model.clientModel.EventClient;
import com.travlendar.travlendarServer.model.clientModel.FreeTimeClient;
import com.travlendar.travlendarServer.model.clientModel.UserClient;
import com.travlendar.travlendarServer.model.dao.*;
import com.travlendar.travlendarServer.model.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Request Handler is responsible to manage and process the external request,for each request mapping we return a
 * response to send to the client. The data inserted validity is handled inside the DAO with default methods.
 */
@Controller
@Transactional
public class RequestHandler {

    /**********DAO AUTOWIRED***********/
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private FreetTimeDao freetTimeDao;
    @Autowired
    private PrivateTransportDao privateTransportDao;
    @Autowired
    private PublicTransportDao publicTransportDao;
    @Autowired
    private EventDao eventDao;
    @Autowired
    private GreenDao greenDao;
    @Autowired
    private TransportSolutionDao transportSolutionDao;
    @Autowired
    private TransportSegmentDao transportSegmentDao;



    @RequestMapping("/get-free-time")
    @ResponseBody
    public String getFreeTime(@RequestParam("user_id") Long userId) {
        Response r=new Response("ok");
        try{
            User u=userDao.findOne(userId);
            ArrayList<FreeTimeClient> freeTimeClients = new ArrayList<>();
            for(FreeTime freeTime: u.getFreeTimes()) freeTimeClients.add(freeTime.getFreeTimeClient());
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonInString = gson.toJson(freeTimeClients);
            return jsonInString;

        }catch(Exception e2){
            r.setMessage("fail: "+e2.getMessage());
        }
        return "fail";
    }

    @RequestMapping("/get-user")
    @ResponseBody
    public String getEvent(@RequestParam("email") String email) {
        Response r=new Response("ok");
        try{
            User u=userDao.findByEmail(email);
            UserClient userClient= u.getUserClient();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonInString = gson.toJson(userClient);
            return jsonInString;

        }catch(Exception e2){
            r.setMessage("fail: "+e2.getMessage());
        }
        return "fail";
    }
    @RequestMapping("/add-event")
    @ResponseBody
    public String addEvent(@RequestParam("user_id") Long userId, @RequestParam("start_date") Timestamp startDate,
                             @RequestParam("end_date")Timestamp endDate,@RequestParam("pos_x") Float posX,
                             @RequestParam("pos_y") Float posY,@RequestParam("description") String description,
                             @RequestParam("name") String name,@RequestParam("end_event")Boolean endEvent) {
        Response r=new Response("ok");
        try{
            //fetch the user
            User u=userDao.findOne(userId);
            //create the event
            Event e=new Event(u,startDate,endDate,posX,posY,description,name,endEvent);
            eventDao.customSave(e);
            r.setMessage("event added into DB");
            replan(u.getId());
        }catch(DataEntryException e1){
            r.setMessage("fail: "+e1.getMessage());
        }catch(Exception e2){
            r.setMessage("fail: "+e2.getMessage());
        }
        return r.getMessage();
    }

    @RequestMapping("/replan")
    @ResponseBody
    public Response replan(@RequestParam("user_id") Long userId) {
        Response r=new Response("ok");
        try{
            //fetch the user
            User u=userDao.findOne(userId);
            ArrayList<EventLogic> listEventLogic = new ArrayList<>();
            //Oracle engineer don't know how to do that
            listEventLogic.addAll(u.getEvents());
            //get the transport solution
            List<TransportSolutionLogic> outputSol = MainLogic.calculateTransportSolutions(listEventLogic,u);
            saveTransportSolutionLogic(outputSol);
            r.setMessage("success");
        }catch(Exception e2){
            r.setMessage("fail: "+e2.getMessage());
        }
        return r;
    }

    private void saveTransportSolutionLogic(List<TransportSolutionLogic> tsl){
        List<TransportSolution> transportSolutions=new ArrayList<>();
        int i=0;
        //compose and save transport solutions
        for (TransportSolutionLogic x:tsl){
            transportSolutions.add((TransportSolution)x);
            TransportSolutionId tsID=new TransportSolutionId(transportSolutions.get(i).getEvent1().getId(),
                    transportSolutions.get(i).getEvent2().getId());
            transportSolutions.get(i).setTransportSolutionId(tsID);
            i++;
        }
        transportSolutionDao.save(transportSolutions);
        //compose and save transport segments
        for(TransportSolution t:transportSolutions) {
            int segmentOrder=0;
            for(TransportSegment transportSegment:t.getTransportSegments()){
                TransportSegmentId transportSegmentId=new TransportSegmentId(segmentOrder,t.getEvent1().getId(),
                        t.getEvent2().getId());
                transportSegment.setTransportSegmentId(transportSegmentId);
                transportSegmentDao.save(transportSegment);
                segmentOrder++;
            }
        }
    }




    @RequestMapping("/delete-event")
    @ResponseBody
    public String deleteEvent(@RequestParam("user_id")Long userId,@RequestParam("event_id")Long eventId) {
        Response r=new Response("ok");
        try{
            User u=userDao.findOne(userId);
            Event e=eventDao.findOne(eventId);
            eventDao.customDelete(e,u);
            r.setMessage("event deleted");
        }catch(DataEntryException e1){
            r.setMessage(e1.getMessage());
        }catch(Exception e2){
            r.setMessage("fail: "+e2.getMessage());
        }
        return r.getMessage();
    }


    @RequestMapping("/update-event")
    @ResponseBody
    public String updateEvent(@RequestParam("user_id") Long userId,@RequestParam("event_id")Long eventId,
                                @RequestParam("start_date") Timestamp startDate,
                                @RequestParam("end_date")Timestamp endDate,@RequestParam("pos_x") Float posX,
                                @RequestParam("pos_y") Float posY,@RequestParam("description") String description,
                                @RequestParam("name") String name,@RequestParam("end_event")Boolean endEvent) {
        Response r=new Response("ok");
        try{
            User u=userDao.findOne(userId);
            Event e=eventDao.findOne(eventId);
            e.completeSet(u,startDate,endDate,posX,posY,description,name,endDate);
            eventDao.customUpdate(e,u);
            r.setMessage("event updated");
        }catch(DataEntryException e1){
            r.setMessage(e1.getMessage());
        }catch(Exception e2){
            r.setMessage("fail: "+e2.getMessage());
        }
        return r.getMessage();
    }


    @RequestMapping("/fetch-events")
    @ResponseBody
    public String fetchEvents(@RequestParam("user_id") Long userId){
        Response r=new Response("ok");
        try{
            User u=userDao.findOne(userId);
            ArrayList<EventClient> eventClients = new ArrayList<EventClient>();
            for(Event event : u.getEvents())  eventClients.add(event.getEventClient());
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.S").setPrettyPrinting().create();
            String jsonInString = gson.toJson(eventClients);
            return jsonInString;
        }catch(Exception e2){
            r.setMessage("fail: "+e2.getMessage());
        }
        return "fail";
    }

    @RequestMapping("/add-private-transport")
    @ResponseBody
    public Response addPrivateTransport(@RequestParam("user_id") Long userId,@RequestParam("name") String name,
                                        @RequestParam ("type")String type,@RequestParam("displacement")int displacement,
                                        @RequestParam("license_plate") String license){
        Response r=new Response("ok");
        try {
            //convert type
            MeanType meanType = MeanType.valueOf(type);
            //fetch correct green
            Green green = greenDao.findByLevel(meanType.getGreenEnum());
            //fetch user owner
            User u = userDao.findOne(userId);
            if(u==null)throw new DataEntryException("user not found");
            //create
            PrivateTransport p = new PrivateTransport(u, name, meanType, displacement, license, green);
            //save
            privateTransportDao.customSave(p);
            //todo aggiungere l' user order
        }catch (DataEntryException e) {
            r.setMessage(e.getMessage());
        }catch(Exception e){
            r.setMessage("fail: "+e.getMessage());
        }
        return r;
    }

    @RequestMapping("/add-order")
    @ResponseBody
    public Response addOrder(@RequestParam("user_id") Long userId,
                             @RequestParam("transport_id") Long transportId,
                             @RequestParam("type_transport") boolean type,
                             @RequestParam("num_order") int numOrder){
        Response r=new Response("ok");
        try{
            //fetch the user
            User u = userDao.findOne(userId);
            UserOrder ur;
            if(type){
                ur = new UserOrder(numOrder,u, null,u.getPrivateTransportById(transportId));
            }else{
                ur = new UserOrder(numOrder,u,publicTransportDao.findOne(transportId),null);
            }

            userOrderDao.customSave(u,ur);

        }catch(Exception e2){
            r.setMessage("fail"+e2.getMessage());
        }
        return r;
    }

    @RequestMapping("/add-free-time")
    @ResponseBody
    public Response addFreeTime(@RequestParam("user_id") Long userId,
                                @RequestParam("start_date") Timestamp startDate,
                                @RequestParam("end_date") Timestamp endDate,
                                @RequestParam("duration") int duration){
        Response r=new Response("ok");
        //fetch the user
        User u = userDao.findOne(userId);
        FreeTime freeTime = new FreeTime(startDate, endDate, duration, u);
        try {
            freetTimeDao.customSave(freeTime);
        }catch(Exception e){
            r.setMessage("fail"+e.getMessage());
        }
        return r;
    }










}
