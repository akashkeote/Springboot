package com.substring.foodie.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uri")
public class UriPathVariable {
    
    @RequestMapping("/{message}/for/{name}")
  // ye message isme kese ayegpublic String wish(STring message ,String name)
 public String wish(@PathVariable("message") String ak_message, @PathVariable String name) {
        return "Hello " + name + ", " + ak_message;
    }
}
