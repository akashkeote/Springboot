package com.firstboot.project.springboot.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/about")
    public String about() {
        System.out.println("rendering about page");
        return "about";
    }

    @RequestMapping("/service")
    public String service() {
        System.out.println("rendering service page");
        return "service";
    }

}
