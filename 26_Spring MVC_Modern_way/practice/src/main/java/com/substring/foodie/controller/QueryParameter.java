package com.substring.foodie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query")
public class QueryParameter {
    //http://localhost:8080/query/message?msg=Hello%20World
    @RequestMapping("/message")
    public String getMessage(@RequestParam("msg") String msg) {
        return "You sent the message: " + msg;
    }
    //thoda aur explore karte
    //http://localhost:8080/query/message2?msg=Hello%20World&sender=Akash
    @RequestMapping("/message2")
    public String getMessage2(@RequestParam(value = "msg",required = true,defaultValue = "jay") String msg, @RequestParam("sender") String sender) {
        return "Message from " + sender + ": " + msg;
    }
}
