package com.substring.foodie.controller;

import java.util.ArrayList;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.substring.foodie.entity.User;


@RequestMapping("/user")
@RestController
//@Controller
public class UserController {

    @RequestMapping("/")
    @ResponseBody
    public String getUser() {
        System.out.println("User getting...");
        return "user_list";
       
    }
    @ResponseBody
    @RequestMapping("/1")
    public int getUse1(){
         return 7;
    }
    //@RestController
    @RequestMapping("/players-list")
    public List<String> getPlayersList() {
        List<String> players = new ArrayList<>();
        players.add("Sachin");
        players.add("Dhoni");
        players.add("Virat");
        return players;
    }
    @RequestMapping("/details")
   public User getUserDetails() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("securePassword");
         user.setAvailable(true);
         

        return user;
    }
}
