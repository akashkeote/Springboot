package com.firstboot.project.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//new line
@SpringBootApplication
//@Configuration
//@ComponentScan("com.first")
//@EnableAutoConfiguration
public class App{


    public static void main(String[] args) {

        //bootstrapping your spring boot application
        SpringApplication.run(App.class, args);
//		HomeController controller=container.getBean("homeController", HomeController.class);
//		System.out.println(controller);


    }

}

