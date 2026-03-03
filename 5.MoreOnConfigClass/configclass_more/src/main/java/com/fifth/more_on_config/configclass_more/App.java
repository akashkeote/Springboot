package com.fifth.more_on_config.configclass_more;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fifth.more_on_config.configclass_more.concepts.Car;
import com.fifth.more_on_config.configclass_more.concepts.Engine;
import com.fifth.more_on_config.configclass_more.config.ConfigClass;

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