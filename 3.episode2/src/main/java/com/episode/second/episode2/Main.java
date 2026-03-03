package com.episode.second.episode2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.episode.second.episode2.classes_mine.Car;
import com.episode.second.episode2.classes_mine.Engine;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		// SpringApplication.run(Main.class, args);
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

		Car car = context.getBean("car", Car.class);

		Engine engine = context.getBean("engine", Engine.class);
		System.out.println("Car's Autowired Engine: " + car.getEngine().getType());
		System.out.println("Direct Engine Bean: " + engine.getType());
	}

}
