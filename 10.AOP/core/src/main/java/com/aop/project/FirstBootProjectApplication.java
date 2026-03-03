package com.aop.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//new line
@SpringBootApplication
// @Configuration
// @ComponentScan(basePackages = {"com.aop.project","pack"})
// @EnableAutoConfiguration
public class FirstBootProjectApplication {

    public static void main(String[] args) {

        // bootstrapping your spring boot application
        SpringApplication.run(FirstBootProjectApplication.class, args);
        // HomeController controller=container.getBean("homeController",
        // HomeController.class);
        // System.out.println(controller);

    }

}
