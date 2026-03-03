package com.qualify;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.qualify.config.ConfigClass;
import com.qualify.concept.Car;
import com.qualify.student.Student;
import com.qualify.student.Teacher;
import com.qualify.qualifier.Human;

public class App {
    public static void main(String[] args) {
        // before old xml configuration
        // ApplicationContext container = new
        // ClassPathXmlApplicationContext("config.xml");
        // after using config class
        // yaha pehle bean bani thi ab spring container bana hai
        // spring conteainer bean banATA HAI
        // phir dependency inject karta hai

        ApplicationContext container = new AnnotationConfigApplicationContext(ConfigClass.class);
        // container
        // Engine engine1 = container.getBean("engine1", Engine.class);
        // engine1.startEngine();
        System.out.println("------------");
        Car car = container.getBean("car", Car.class);
        car.start();

        Student student = container.getBean("student", Student.class);
        System.out.println(student);
        student.show();

        Teacher teacher = container.getBean("teacher", Teacher.class);
        System.out.println(teacher);

        Human akash = container.getBean("human", Human.class);
        akash.tryColdDrink();

    }
}

/*
 * 
 * 
 * The control of creating and managing objects is
 * transferred to a container (Spring IoC Container).
 * It allows loose coupling between objects.
 * Design Principle : Spring IOC Container
 */