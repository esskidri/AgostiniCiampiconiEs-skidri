package com.travlendar.travlendarServer.controller;


import com.travlendar.travlendarServer.model.Event;
import com.travlendar.travlendarServer.model.TransportSolution;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;


@RestController
public class HelloGreeting {
    @RequestMapping("/greet")
    public String helloGreeting() {
        Timestamp t = new Timestamp((System.currentTimeMillis()));
        Event e = new Event(1, t,t, (float) 1.000,(float)1.000, "okok", new TransportSolution(1,1), new TransportSolution(2,1),1,"rax");

        return "Hello REST";
    }



}
 