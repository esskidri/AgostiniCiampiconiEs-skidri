package com.travlendar.travlendarServer.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloGreeting {
    @RequestMapping("/greet")
    public String helloGreeting() {
        return "Hello REST";
    }

}
 