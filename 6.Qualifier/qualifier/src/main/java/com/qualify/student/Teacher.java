package com.qualify.student;

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