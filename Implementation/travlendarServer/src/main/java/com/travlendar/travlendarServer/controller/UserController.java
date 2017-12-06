package com.travlendar.travlendarServer.controller;



// Imports ...

import com.travlendar.travlendarServer.model.PrivateTransport;
import com.travlendar.travlendarServer.model.User;
import com.travlendar.travlendarServer.model.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    /**
     * GET /create  --> Create a new user and save it in the database.
     */
    @RequestMapping("/create")
    @ResponseBody
    public String create(@RequestParam("email") String email,@RequestParam("fn") String f_name,@RequestParam("ln") String l_name,@RequestParam("age") int age,@RequestParam("sex") String sex) {
        String mpolicy = "green";
        String userId="";
        try {
            User user = new User(f_name,l_name,email,age,sex,null,mpolicy);
            userDao.save(user);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created with id = " + userId;
    }

    @RequestMapping("/userDescription")
    @ResponseBody
    public String userDescription(@RequestParam("id") int id) {
        String desc="";
        try {
            User user = userDao.findById(id);
            desc+="LName: "+user.getLast_name()+"\n";
            desc+="FName: "+user.getFirst_name()+"\n";
            desc+="mail:  "+user.getEmail()+"\n";
            desc+="fcode: "+user.getFiscal_code()+"\n";
            desc+="sex:   "+user.getSex()+"\n";
            desc+="age:   "+user.getAge()+"\n";
            desc+="policy:"+user.getPolicy()+"\n";
            desc+="numbPMeans:"+user.getPrivateTransportList().size()+"  name of first:   "+user.getPrivateTransportList().get(0).getName()+"\n";
            System.out.println(desc);
            PrivateTransport p1=user.getPrivateTransportList().get(0);
            p1.setName("TESLAROADSTER");
            userDao.save(user);

        }
        catch (Exception ex) {
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
    public String getByEmail(String email) {
        String userId = "";
        try {
            User user = userDao.findByEmail(email);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }


    /**
     * GET /update  --> Update the email and the name for the user in the
     * database having the passed id.
     */
    /*
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
    }*/

    // Private fields

    @Autowired
    private UserDao userDao;

}
