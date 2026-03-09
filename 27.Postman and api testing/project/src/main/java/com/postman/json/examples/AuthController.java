package com.postman.json.examples;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    //decalre 
    //use karna hai ab 
    private Logger logger = LoggerFactory.getLogger(AuthController.class);
  @RequestMapping("/login")
    public List<String> login(@RequestBody List<String> li) {
   logger.info("Login request received with data: {}", li);
   return li;
        // Login logic here
    }
    @RequestMapping("/login1")
    public LoginRequest login1(@RequestBody LoginRequest login) {
        logger.info("Login1 request received with data: {}", login);
        return login;
    }

    @RequestMapping("/signup/")
    public String signup(@RequestBody UserData userData) {
        // Signup logic here
        // directly print mat kiya karo log use karo
        logger.info("User signed up: {}{}", userData.getUsername(), 7);
        logger.info("User email: {}", userData.getEmail());
        logger.info("User password: {}", userData.getPassword());
        return "we got data";
    }

}
