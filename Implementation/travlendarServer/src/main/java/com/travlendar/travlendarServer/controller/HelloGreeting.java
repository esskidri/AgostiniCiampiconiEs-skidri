package com.travlendar.travlendarServer.controller;

import com.sun.jmx.snmp.Timestamp;
import com.travlendar.travlendarServer.model.Event;
import com.travlendar.travlendarServer.model.TransportSolution;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloGreeting {
    @RequestMapping("/greet")
    public String helloGreeting() {
        Timestamp t= new Timestamp(11111111);
        Event e = new Event(1, t, t, 1.0,1.0, "okok", new TransportSolution(1,1), new TransportSolution(2,1),true);
        return "Hello REST";
    }



}
