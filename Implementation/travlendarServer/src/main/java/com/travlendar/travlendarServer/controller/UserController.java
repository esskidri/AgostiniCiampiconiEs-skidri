package com.travlendar.travlendarServer.controller;

// Imports ...

import com.travlendar.travlendarServer.controller.Exception.UserException;
import com.travlendar.travlendarServer.extra.Converter;
import com.travlendar.travlendarServer.logic.MainLogic;
import com.travlendar.travlendarServer.logic.modelInterface.EventLogic;
import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.logic.modelInterface.TransportSolutionLogic;
import com.travlendar.travlendarServer.model.Policy;
import com.travlendar.travlendarServer.model.dao.*;
import com.travlendar.travlendarServer.model.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@Transactional
public class UserController {
    @Autowired
    private UserDao userDao;


    @RequestMapping("/createUser")
    @ResponseBody
    public String create(@RequestParam("email") String email, @RequestParam("fn") String f_name,
                         @RequestParam("ln") String l_name, @RequestParam("age") int age,
                         @RequestParam("sex") String sex,@RequestParam("policy")Policy policy) throws Exception {

          //validator.request
        
          User user = new User(f_name,l_name,email,age,sex,null,policy);
          userDao.save(user);
          return "User succesfully created";
    }






    @RequestMapping("/user-description")
    @ResponseBody
    public String userDescription(@RequestParam("id") int id) {
        String desc = "";
        try {
            User user = userDao.findById(id);
            desc += "LName: " + user.getLast_name() + "\n";
            desc += "FName: " + user.getFirst_name() + "\n";
            desc += "mail:  " + user.getEmail() + "\n";
            desc += "fcode: " + user.getFiscal_code() + "\n";
            desc += "sex:   " + user.getSex() + "\n";
            desc += "age:   " + user.getAge() + "\n";
            desc += "policy:" + user.getPolicy().toString() + "\n";
            System.out.println(desc);

        } catch (Exception ex) {
            return "User not found";
        }
        return desc;
    }


    /**
     * GET /delete  --> Delete the user having the passed id.
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            //User user = new User(id);
            //userDao.delete(user);
        } catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "User succesfully deleted!";
    }

    /**
     * GET /get-by-email  --> Return the id for the user having the passed
     * email.
     */
    @RequestMapping("/get-by-email")
    @ResponseBody
    public String getByEmail(String email) {
        String userId = "";
        try {
            User user = userDao.findByEmail(email);
            userId = String.valueOf(user.getId());
        } catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }

    @Autowired
    private FreetTimeDao freetTimeDao;

    @Autowired
    private TransportSolutionDao transportSolutionDao;

    @Autowired
    private TransportSegmentDao transportSegmentDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserOrderDao userOrderDao;


    @RequestMapping("/free")
    @ResponseBody
    public String free() {
        try {
            User user = userDao.findOne((long) 5);
            List<FreeTime> freeTimes = (List<FreeTime>) freetTimeDao.findAll();
            List<TransportSolution> transportSolutions = (List<TransportSolution>) transportSolutionDao.findAll();
            List<TransportSegment> transportSegments = (List<TransportSegment>) transportSegmentDao.findAll();
            TransportSegmentId transportSegmentId = new TransportSegmentId((long) 0, (long) 9, (long) 13);
            TransportSegment transportSegment = transportSegmentDao.findOne(transportSegmentId);
            List<Event> event = (List<Event>) eventDao.findAll();

        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return " successfully ";
    }

    @RequestMapping("/preferences")
    @ResponseBody
    public String preferences() {
        try {
            List<User> users = (List<User>) userDao.findAll();
            List<Event> events = (List<Event>) eventDao.findAll();

        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return " successfully ";
    }

    @RequestMapping("/eventok")
    @ResponseBody
    public String delete() {
        try {
            eventDao.delete((long) 8);
        } catch (Exception ex) {
            return "Error : " + ex.toString();
        }
        return " Success";
    }

    @RequestMapping("/event")
    @ResponseBody
    public String event() {
       List<Event> events= (List<Event>) eventDao.findAll();
       Event e=events.get(0);
       e.setDescription("ciao");



        return " Succes";
    }



    @RequestMapping("/fetch")
    @ResponseBody
    public String fetch() {
       User user=userDao.findOne((long) 6);
       MainLogic mainLogic=new MainLogic();
       List<EventLogic> eventLogics=new ArrayList<>();

       Event e1=eventDao.findOne((long)31);
       Event e2=eventDao.findOne((long)32);
       eventLogics.add(e1);
       eventLogics.add(e2);
       //computation
       List<TransportSolutionLogic> tsl=mainLogic.calculateTransportSolutions(eventLogics,user);
       //save
       saveTransportSolutionLogic(tsl);

       return "ok";

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


    //TODO re-add Fetch JSON


    @RequestMapping("/transportSolution")
    @ResponseBody
    public String transportSolutionRequest() {
        List<User> us = (List<User>) userDao.findAll();
        User u = us.get(0);

        Timestamp t1 = Converter.createTimeStampFromDate("11/09/2007/2:00");
        Timestamp t2 = Converter.createTimeStampFromDate("11/09/2007/3:00");
        Timestamp t3 = Converter.createTimeStampFromDate("11/09/2007/4:00");
        Timestamp t4 = Converter.createTimeStampFromDate("11/09/2007/6:00");

        Event e1 = new Event(t1, t2, (float) 45, (float) 46.0, "nuoto", "nuoto", false);
        Event e2 = new Event(t3, t4, (float) 45, (float) 46.0, "palestra", "palestra", false);

        e1.setUser(u);
        e2.setUser(u);
        //validator-->

        //save-->
        eventDao.save(e1);
        eventDao.save(e2);
        //TS-->
        TransportSolutionId tsId = new TransportSolutionId(e1.getId(), e2.getId());
        TransportSolution transportSolution = new TransportSolution(e1, e2, tsId, null);
        transportSolutionDao.save(transportSolution);
        String s = " " + e1.getId();
        return s;
   }





}
