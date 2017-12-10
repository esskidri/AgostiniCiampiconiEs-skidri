package com.travlendar.travlendarServer.controller;

// Imports ...

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.travlendar.travlendarServer.controller.Exception.UserException;
import com.travlendar.travlendarServer.extra.Converter;
import com.travlendar.travlendarServer.model.dao.*;
import com.travlendar.travlendarServer.model.domain.*;
import net.minidev.json.JSONObject;
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
                         @RequestParam("sex") String sex,@RequestParam("policy")String policy) throws Exception {

          //validator.request
        
          User user = new User(f_name,l_name,email,age,sex,null,policy);
          userDao.save(user);
          return "User succesfully created";
    }

    @ResponseStatus(value=HttpStatus.FORBIDDEN,reason="user Exception")
    @ExceptionHandler(UserException.class)
    public String handleUserException(UserException ex) {
          return ex.getMessage();
    }



    @ResponseStatus(value=HttpStatus.UNAUTHORIZED,reason="MIAOASDAOSDASOD error")
    @ExceptionHandler(Exception.class)
    public String handleCustomException(UserException ex) {
        return ex.getMessage();
    }





    @RequestMapping("/userDescription")
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
            desc += "policy:" + user.getPolicy() + "\n";
            //desc+="numbPMeans:"+user.getPrivateTransportList().size()+"  name of first:   "+user.getPrivateTransportList().get(0).getName()+"\n";
            System.out.println(desc);
            PrivateTransport p1 = user.getPrivateTransportList().get(0);
            //p1.setName("TESLAROADSTER");
            userDao.save(user);

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
            System.out.println("AVARAAAAAAAAAAAAA");
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return " succesfully ";
    }

    @RequestMapping("/preferences")
    @ResponseBody
    public String preferences() {
        try {
            List<User> users = (List<User>) userDao.findAll();
            List<Event> events = (List<Event>) eventDao.findAll();
            System.out.println("AVARAAAAAAAAAAAAA");
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return " succesfully ";
    }

    @RequestMapping("/event")
    @ResponseBody
    public String delete() {
        try {
            eventDao.delete((long) 8);
        } catch (Exception ex) {
            return "Error : " + ex.toString();
        }
        return " Succes";
    }

    @RequestMapping("/fetch")
    @ResponseBody
    public String fetch() {
        return "okokok";
    }

    @RequestMapping("/fetchJson")
    @ResponseBody
    public JSONObject fetchJson() {

        String message;
        JSONObject json = new JSONObject();
        json.put("name", "student");

        return json;
    }


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
