package com.travlendar.travlendarServer.controller;

import com.travlendar.travlendarServer.model.domain.PrivateTransport;
import com.travlendar.travlendarServer.model.dao.PrivateTransportDao;
import com.travlendar.travlendarServer.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PrivateTransportController {

    @Autowired
    private PrivateTransportDao ptDao;
    /**
     * GET /create  --> Create a new user and save it in the database.
     */
    @RequestMapping("/createPT")
    @ResponseBody
    public String create(@RequestParam("name") String name, @RequestParam("type") String type, @RequestParam("disp") int displacement, @RequestParam("gId") int greenId, @RequestParam("lp") String license) {
        String pId="";
        try {
          //  PrivateTransport privateTransport=new PrivateTransport(name,type,displacement,greenId,license);
          //  ptDao.save(privateTransport);
          //  pId = String.valueOf(privateTransport.getId());
        }
        catch (Exception ex) {
            return "Error creating the private Mean: " + ex.toString();
        }
        return "Private Mean succesfully created with id = " + pId;
    }

    @RequestMapping("/userOwner")
    @ResponseBody
    public String userOwner(@RequestParam("id") Long id){
        PrivateTransport privateTransport=new PrivateTransport();
        try {
            privateTransport=ptDao.findOne(id);
        }
        catch (Exception ex) {
            return "Error Fetching " + ex.toString();
        }
        List<User> users=privateTransport.getUsers();
        for(User u:users){
            System.out.println(u.getFirst_name());
            System.out.println(u.getLast_name());
        }
        return "ok";

    }


}
