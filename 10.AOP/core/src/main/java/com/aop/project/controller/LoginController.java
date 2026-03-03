package com.aop.project.controller;

import com.aop.project.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/")
    public String loginPage() {
        System.out.println("this is login page");
        boolean isLogin = loginService.doLogin();
        if (isLogin) {
            return "success_login";
        } else {
            return "login";
        }

    }
}
