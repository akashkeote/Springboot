package com.qualify.student;

import org.springframework.stereotype.Component;

@Component
public class Student {

    public Student() {
        System.out.println("Creating Student Object");
    }

    public void show() {
        System.out.println("I am student");
    }
}