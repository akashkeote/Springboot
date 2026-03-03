package com.fourth.configclass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fourth.configclass.concepts.Car;
import com.fourth.configclass.concepts.ConfigClass;
import com.fourth.configclass.concepts.Engine;

public class App {
    public static void main(String[] args) {
        System.out.println("project started");
        System.out.println("Starting point");

//        ApplicationContext container = new ClassPathXmlApplicationContext("config.xml");

        ApplicationContext container = new AnnotationConfigApplicationContext(ConfigClass.class);
        //container
 Engine engine1 = container.getBean("engine1", Engine.class);
        engine1.startEngine();
        System.out.println("------------");
        Car car = container.getBean("car", Car.class);
        car.start();


    }
}

/*


The control of creating and managing objects is
 transferred to a container (Spring IoC Container).
 It allows loose coupling between objects.
 Design Principle : Spring IOC Container
 */