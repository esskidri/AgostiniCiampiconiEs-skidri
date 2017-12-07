package com.travlendar.travlendarServer.dataManager;

import com.travlendar.travlendarServer.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestHandler {

    @RequestMapping("/add-event")
    @ResponseBody
    public String addEvent() {



        return "";
    }





}
