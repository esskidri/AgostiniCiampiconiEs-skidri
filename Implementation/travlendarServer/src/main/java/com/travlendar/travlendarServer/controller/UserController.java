package com.travlendar.travlendarServer.controller;


import com.travlendar.travlendarServer.model.User;
import com.travlendar.travlendarServer.model.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    /**
     * GET /create  --> Create a new user and save it in the database.
     */
    @RequestMapping("/create")
    @ResponseBody
    public String create(@RequestParam ("email") String email2, String name) {
        String userId = "";
        try {
            User user = new User(email2, name);
            userDao.save(user);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created with id = " + userId;
    }


    /**
     * GET /delete  --> Delete the user having the passed id.
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            User user = new User(id);
            userDao.delete(user);
        }
        catch (Exception ex) {
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
    public String getByEmail(@RequestParam("email") String email) {
        String userId = "";
        String name="";
        try {
           // List<User> users = userDao.findByEmail(email);
            name = userDao.findByEmail(email);
           // userId = String.valueOf(users.get(0).getId());
        }
        catch (Exception ex) {
            return "User not found"+ex.toString();
        }
        return "The user id is: " + name;
    }

    /**
     * GET /update  --> Update the email and the name for the user in the
     * database having the passed id.
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(long id, String email, String name) {
        try {
            User user = userDao.findOne(id);
            user.setEmail(email);
            user.setName(name);
            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }

    // Private fields

    @Autowired
    private UserDao userDao;

}