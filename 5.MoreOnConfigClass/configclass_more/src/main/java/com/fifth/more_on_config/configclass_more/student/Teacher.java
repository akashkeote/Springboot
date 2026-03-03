package com.fifth.more_on_config.configclass_more.student;

import org.springframework.stereotype.Component;

@Component("teacher")
public class Teacher {
    public Teacher() {
        System.out.println("Creating Teacher Object");
    }

    public void show() {
        System.out.println("I am teacher");
    }
}