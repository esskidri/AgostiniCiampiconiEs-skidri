package com.travlendar.travlendarServer.controller.dataManager;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.travlendar.travlendarServer.controller.Exception.DataEntryException;
import com.travlendar.travlendarServer.logic.MainLogic;
import com.travlendar.travlendarServer.logic.modelInterface.EventLogic;
import com.travlendar.travlendarServer.logic.modelInterface.TransportSolutionLogic;
import com.travlendar.travlendarServer.model.enumModel.EnumGreenLevel;
import com.travlendar.travlendarServer.model.enumModel.MeanType;
import com.travlendar.travlendarServer.model.clientModel.EventClient;
import com.travlendar.travlendarServer.model.clientModel.FreeTimeClient;
import com.travlendar.travlendarServer.model.clientModel.UserClient;
import com.travlendar.travlendarServer.model.dao.*;
import com.travlendar.travlendarServer.model.domain.*;
import com.travlendar.travlendarServer.model.enumModel.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.*;

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


    @RequestMapping("/create-user")
    @ResponseBody
    public String create(@RequestParam("email") String email, @RequestParam("fn") String f_name,
                         @RequestParam("ln") String l_name, @RequestParam("age") int age,

                         @RequestParam("sex") String sex) throws Exception {
        try {
            User user = new User(f_name, l_name, email, age, sex, null, Policy.GREEN);
            userDao.save(user);
            addPrivateTransport(user.getId(), "walk", MeanType.WALKING.toString().toUpperCase(), 0, " ");
            return "User succesfully created";
        }catch (Exception e){
            return "fail: " + e.getMessage();
        }
    }

    @RequestMapping("/get-free-time")
    @ResponseBody
    public String getFreeTime(@RequestParam("user_id") Long userId) {
        Response r = new Response("ok");
        try {
            User u = userDao.findOne(userId);
            ArrayList<FreeTimeClient> freeTimeClients = new ArrayList<>();
            for (FreeTime freeTime : u.getFreeTimes()) freeTimeClients.add(freeTime.getFreeTimeClient());
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(freeTimeClients);

        } catch (Exception e2) {
            r.setMessage("fail: " + e2.getMessage());
        }
        return "fail";
    }

    @RequestMapping("/get-user")
    @ResponseBody
    public String getEvent(@RequestParam("email") String email) {
        Response r = new Response("ok");
        try {
            User u = userDao.findByEmail(email);
            UserClient userClient = u.getUserClient();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(userClient);

        } catch (Exception e2) {
            r.setMessage("fail: " + e2.getMessage());
        }
        return "fail";
    }

    @RequestMapping("/add-event")
    @ResponseBody
    public String addEvent(@RequestParam("user_id") Long userId, @RequestParam("start_date") Timestamp startDate,
                           @RequestParam("end_date") Timestamp endDate, @RequestParam("pos_x") Float posX,
                           @RequestParam("pos_y") Float posY, @RequestParam("description") String description,
                           @RequestParam("name") String name, @RequestParam("end_event") Boolean endEvent) {
        Response r = new Response("ok");
        try {
            //fetch the user
            User u = userDao.findOne(userId);
            //create the event
            Event e = new Event(u, startDate, endDate, posX, posY, description, name, endEvent);
            eventDao.customSave(e);

            List<Event> eventList = u.getEvents();
            orderEvents(eventList);

            List<EventLogic> userEvents = new ArrayList<>();
            userEvents.addAll(eventList);
            List<EventLogic> eventLogics = MainLogic.getDailyEventsForReplan(userEvents, e.getStartDate(), e.getEndDate());

            List<Event> events = new ArrayList<>();
            for(EventLogic eventLogic: eventLogics)
                events.add((Event) eventLogic);
            events.remove(e);
            clearTransportSolution(events);
            //computation
            List<TransportSolutionLogic> tsl = MainLogic.calculateTransportSolutions(eventLogics, u);

            //save
            saveNewEvent(eventLogics, u);
            saveTransportSolutionLogic(tsl);
            r.setMessage("event added into DB");
        } catch (DataEntryException e1) {
            r.setMessage("fail: " + e1.getMessage());
            System.out.println(e1.getMessage());
        } catch (Exception e2) {
            r.setMessage("fail: " + e2.getMessage());
            System.out.println(e2.getMessage());
        }
        return r.getMessage();
    }

    @RequestMapping("/replan")
    @ResponseBody
    public String replan(@RequestParam("user_id") Long userId) {
        Response r = new Response("ok");
        try {
            //fetch the user
            User u = userDao.findOne(userId);
            ArrayList<EventLogic> eventLogics = new ArrayList<>();
            eventLogics.addAll(u.getEvents());
            clearTransportSolution(u.getEvents());
            //get the transport solution
            List<TransportSolutionLogic> outputSol = MainLogic.calculateTransportSolutions(eventLogics, u);
            saveNewEvent(eventLogics, u);
            saveTransportSolutionLogic(outputSol);
            r.setMessage("success");
        } catch (Exception e2) {
            r.setMessage("fail: " + e2.getMessage());
        }
        return r.getMessage();
    }

    @RequestMapping("/deleteTS")
    @ResponseBody
    public String deleteTS() {
        TransportSolution solution=null;
        int i=0;
       for(TransportSolution t:transportSolutionDao.findAll()){
           if(i==0) {
               solution = t;
           }
       }
       transportSolutionDao.delete(solution);
       return "ok";
    }







    @RequestMapping("/delete-event")
    @ResponseBody
    public String deleteEvent(@RequestParam("user_id") Long userId, @RequestParam("event_id") Long eventId) {
        Response r = new Response("ok");
        try {
            User u = userDao.findOne(userId);
            Event e = eventDao.findOne(eventId);

            List<EventLogic> userEvent = new ArrayList<>();

            userEvent.addAll(u.getEvents());
            List<EventLogic> eventLogics = MainLogic.getDailyEventsForReplan(userEvent, e.getStartDate(), e.getEndDate());

            List<Event> events = new ArrayList<>();
            for(EventLogic eventLogic: eventLogics)
                events.add((Event) eventLogic);
            clearTransportSolution(events);

            eventDao.customDelete(e, u);
            eventLogics.remove(e);

            //computation
            List<TransportSolutionLogic> tsl = MainLogic.calculateTransportSolutions(eventLogics, u);
            saveNewEvent(eventLogics, u);
            saveTransportSolutionLogic(tsl);


            r.setMessage("event deleted");
        } catch (DataEntryException e1) {
            r.setMessage(e1.getMessage());
        } catch (Exception e2) {
            r.setMessage("fail: " + e2.getMessage());
        }
        return r.getMessage();
    }


    @RequestMapping("/update-event")
    @ResponseBody
    public String updateEvent(@RequestParam("user_id") Long userId, @RequestParam("event_id") Long eventId,
                              @RequestParam("start_date") Timestamp startDate,
                              @RequestParam("end_date") Timestamp endDate, @RequestParam("pos_x") Float posX,
                              @RequestParam("pos_y") Float posY, @RequestParam("description") String description,
                              @RequestParam("name") String name, @RequestParam("end_event") Boolean endEvent) {
        Response r = new Response("ok");

        //todo control
        try {
            User u = userDao.findOne(userId);
            Event e = eventDao.findOne(eventId);
            Event temporaryE = new Event();
            temporaryE.completeSet(e.getUser(),e.getStartDate(),e.getEndDate(),e.getPosX(), e.getPosY(),e.getDescription(),e.getName(),e.isEndEvent());
            temporaryE.setId(e.getId());

            List<EventLogic> userEvents = new ArrayList<>();
            userEvents.addAll(u.getEvents());
            List<EventLogic> eventLogics1 = MainLogic.getDailyEventsForReplan(userEvents, e.getStartDate(), e.getEndDate());
            e.completeSet(u, startDate, endDate, posX, posY, description, name, endEvent);
            List<EventLogic> eventLogics2 = MainLogic.getDailyEventsForReplan(userEvents, e.getStartDate(), e.getEndDate());


            List<Event> events = new ArrayList<>();
            for(EventLogic eventLogic: eventLogics2)
                events.add((Event) eventLogic);
            clearTransportSolution(events);
            saveTransportSolutionLogic(MainLogic.calculateTransportSolutions(eventLogics2, u));
            eventDao.customUpdate(e, u);
            saveNewEvent(eventLogics2, u);

            if(!eventLogics2.equals(eventLogics1)){
                events.clear();
                eventLogics1.remove(temporaryE);
                for(EventLogic eventLogic: eventLogics1)
                    events.add((Event) eventLogic);
                clearTransportSolution(events);
                saveTransportSolutionLogic(MainLogic.calculateTransportSolutions(eventLogics1, u));
            }



            eventDao.customUpdate(e, u);
            r.setMessage("event updated");
        } catch (DataEntryException e1) {
            r.setMessage(e1.getMessage());
        } catch (Exception e2) {
            r.setMessage("fail: " + e2.getMessage());
        }
        return r.getMessage();
    }


    @RequestMapping("/fetch-events")
    @ResponseBody
    public String fetchEvents(@RequestParam("user_id") Long userId) {
        Response r = new Response("ok");
        try {
            User u = userDao.findOne(userId);
            ArrayList<EventClient> eventClients = new ArrayList<EventClient>();
            for (Event event : u.getEvents()) eventClients.add(event.getEventClient());
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.S").setPrettyPrinting().create();
            String jsonInString = gson.toJson(eventClients);
            return jsonInString;
        } catch (Exception e2) {
            r.setMessage("fail: " + e2.getMessage());
        }
        return r.getMessage();
    }

    @RequestMapping("/add-private-transport")
    @ResponseBody
    public String addPrivateTransport(@RequestParam("user_id") Long userId,@RequestParam("name") String name,
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
            if (u == null) throw new DataEntryException("user not found");
            //create
            PrivateTransport p = new PrivateTransport(u, name, meanType, displacement, license, green);
            //save
            p=privateTransportDao.customSave(p);
            u=userDao.save(u);
            addOrder(u.getId(),p.getId(),p.isPrivate());
            r.setMessage("mean added ");
        } catch (DataEntryException e) {
            r.setMessage(e.getMessage());
        } catch (Exception e) {
            r.setMessage("fail: " + e.getMessage());
        }
        return r.getMessage();
    }

    @RequestMapping("/add-public-transport")
    @ResponseBody
    public String addPrivateTransport(@RequestParam("user_id") Long userId,
                                      @RequestParam("transport_id") Long transportId){
        Response r=new Response("ok");
        try {
            User u = userDao.findOne(userId);
            if (u == null) throw new DataEntryException("user not found");
            //fetch public transport
            PublicTransport p= publicTransportDao.findOne(transportId);
            //save
            u.getPublicTransportList().add(p);
            p.addUser(u);
            p=publicTransportDao.save(p);
            u=userDao.save(u);
            addOrder(u.getId(),p.getId(),p.isPrivate());
            r.setMessage("mean added ");
        } catch (DataEntryException e) {
            r.setMessage(e.getMessage());
        } catch (Exception e) {
            r.setMessage("fail: " + e.getMessage());
        }
        return r.getMessage();
    }

    @RequestMapping("delete-private-transport")
    @ResponseBody
    public String deletePrivateTransport(@RequestParam("user_id") Long userId,
                                      @RequestParam("transport_id") Long transportId){
        Response r=new Response("ok");
        try {
            User u = userDao.findOne(userId);
            if (u == null) throw new DataEntryException("user not found");
            privateTransportDao.delete(transportId);
            UserOrder userOrderToDelete=u.getUserOrdeByPrivateTransportId(transportId);
            int orderDeleted = userOrderToDelete.getOrder();
            userOrderDao.delete(userOrderToDelete);
            List<UserOrder> temp = sortByNumOrder(u.getUserOrders());
            compactOrder(u,orderDeleted);
            r.setMessage("mean deleted ");
        } catch (DataEntryException e) {
            r.setMessage(e.getMessage());
        } catch (Exception e) {
            r.setMessage("fail: " + e.getMessage());
        }
        return r.getMessage();
    }

    @RequestMapping("delete-public-transport")
    @ResponseBody
    public String deletePublicTransport(@RequestParam("user_id") Long userId,
                                         @RequestParam("transport_id") Long transportId){
        Response r=new Response("ok");
        try {
            User u = userDao.findOne(userId);
            PublicTransport publicTransport = publicTransportDao.findOne(transportId);
            if (u == null) throw new DataEntryException("user not found");
            UserOrder userOrderToDelete=u.getUserOrdeByPublicTransportId(transportId);
            u.getPublicTransportList().remove(publicTransport);
            publicTransport.getUsers().remove(u);
            publicTransport=publicTransportDao.save(publicTransport);
            u=userDao.save(u);
            int orderDeleted = userOrderToDelete.getOrder();
            userOrderDao.delete(userOrderToDelete);
            List<UserOrder> temp = sortByNumOrder(u.getUserOrders());
            compactOrder(u,orderDeleted);
            r.setMessage("mean deleted ");
        } catch (DataEntryException e) {
            r.setMessage(e.getMessage());
        } catch (Exception e) {
            r.setMessage("fail: " + e.getMessage());
        }
        return r.getMessage();
    }

    public void compactOrder(User u, int delimiter){
        for (UserOrder elem : u.getUserOrders()) {
            if(elem.getOrder() > delimiter) elem.setOrder(elem.getOrder()-1);
        }
    }

    public int getDelimiter(List<UserOrder> list){
        if(list.get(0).getOrder() == 2) return 1;
        for(int i = 0; i<list.size()-1;i++){
            if(list.get(i).getOrder()+1 != list.get(i+1).getOrder()) return i;
        }
        return list.size()-1;
    }

    public List<UserOrder> sortByNumOrder(List<UserOrder> list){
        sort(list,new Comparator<UserOrder>() {
            @Override
            public int compare(UserOrder lhs, UserOrder rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getOrder() < rhs.getOrder() ? -1 : (lhs.getOrder() > rhs.getOrder()) ? 1 : 0;
            }
        });
        return list;
    }





    @RequestMapping("/delete-all-order")
    @ResponseBody
    public String addOrder(@RequestParam("user_id") Long userId){
        Response r=new Response("ok");
        try{
            User u = userDao.findOne(userId);
            userOrderDao.delete(u.getUserOrders());
            userDao.save(u);
            r.setMessage("order deleted");
        }catch(Exception e2){
            r.setMessage("fail"+e2.getMessage());
        }
        return r.getMessage();
    }

    @RequestMapping("/add-order")
    @ResponseBody
    public String addOrder(@RequestParam("user_id") Long userId,
                             @RequestParam("transport_id") Long transportId,
                             @RequestParam("type_transport") boolean type){
        Response r=new Response("ok");
        try{
            //fetch the user
            User u = userDao.findOne(userId);
            UserOrder ur;
            if(type){
                ur = new UserOrder(u.getUserOrders().size()+1,u, null,privateTransportDao.findOne(transportId));
            }else{
                ur = new UserOrder(u.getUserOrders().size()+1,u,publicTransportDao.findOne(transportId),null);
            }
            userOrderDao.customSave(u,ur);
            r.setMessage("mean added");
        }catch(Exception e2){
            r.setMessage("fail"+e2.getMessage());
        }
        return r.getMessage();
    }

    @RequestMapping("/set-policy")
    @ResponseBody
    public String addFreeTime(@RequestParam("user_id") Long userId,
                              @RequestParam("policy") String policy) {
        Response r=new Response("ok");
        try{
            //fetch the user
            User u = userDao.findOne(userId);
            policy = policy.toUpperCase();
            switch (policy){
                case "GREEN": u.setPolicy(Policy.GREEN);
                              break;
                case "FAST":  u.setPolicy(Policy.FAST);
                              break;
                case "CHEAP": u.setPolicy(Policy.CHEAP);
                              break;
                default:      u.setPolicy(Policy.GREEN);
                              break;
            }
            userDao.save(u);
            r.setMessage("policy setted");
        }catch(Exception e2){
            r.setMessage("fail"+e2.getMessage());
        }
        return r.getMessage();
    }



    @RequestMapping("/add-free-time")
    @ResponseBody
    public String addFreeTime(@RequestParam("user_id") Long userId,
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
        return r.getMessage();
    }


    /*** Request Handler support methods ***/


    private void saveNewEvent(List<EventLogic> eventLogics, User u) {
        List<Event> eventsToBeAdded = new ArrayList<>();

        for (EventLogic event : eventLogics)
            if (!u.getEvents().contains(event)) {
                eventsToBeAdded.add((Event) event);
            }

        for (Event event : eventsToBeAdded) {
            try {
                eventDao.customSave(event);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    private void saveTransportSolutionLogic(List<TransportSolutionLogic> tsl) {
        List<TransportSolution> transportSolutions = new ArrayList<>();
        int i = 0;
        //compose and save transport solutions
        for (TransportSolutionLogic x : tsl) {
            transportSolutions.add((TransportSolution) x);
            TransportSolutionId tsID = new TransportSolutionId(transportSolutions.get(i).getEvent1().getId(),
                    transportSolutions.get(i).getEvent2().getId());
            transportSolutions.get(i).setTransportSolutionId(tsID);
            i++;
        }
        transportSolutionDao.save(transportSolutions);
        //compose and save transport segments
        for (TransportSolution t : transportSolutions) {
            int segmentOrder = 0;
            for (TransportSegment transportSegment : t.getTransportSegments()) {
                TransportSegmentId transportSegmentId = new TransportSegmentId(segmentOrder, t.getEvent1().getId(),
                        t.getEvent2().getId());
                transportSegment.setTransportSegmentId(transportSegmentId);
                transportSegmentDao.save(transportSegment);
                segmentOrder++;
            }
        }
    }


    private void clearTransportSolution(List<Event> events) throws Exception{
        List<TransportSolution> transportSolutions;
        List<TransportSegment> transportSegments;

        for (Event event : events) {
            transportSolutions = new ArrayList<>();
            transportSegments= new ArrayList<>();
            if(event.getTransportSolutions() != null)
                transportSolutions.addAll(event.getTransportSolutions());
            for (TransportSolution transportSolution : transportSolutions) {
                transportSegments.addAll(transportSolution.getTransportSegments());
            }
            transportSegmentDao.delete(transportSegments);
            transportSolutionDao.delete(transportSolutions);
            System.out.println(event.getId());
        }
        System.out.println("ok");
    }

    private void orderEvents(List<Event> events){
        for(int i = 0; i < events.size() -1; i++){
            for(int j = i +1; j < events.size(); j++){
                if(events.get(i).compareTo(events.get(j)) > 0)
                    swapEvents(events, i, j);
            }
        }
    }

    private void swapEvents(List<Event> events, int i, int j) {
        Event event1 = events.get(i);
        Event event2 = events.get(j);

        events.remove(i);
        events.remove(j -1);

        events.add(i, event2);
        events.add(j, event1);
    }


}
