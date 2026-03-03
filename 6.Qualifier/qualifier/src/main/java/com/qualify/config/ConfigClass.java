package com.qualify.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.qualify.student.Teacher;

@Configuration
@ComponentScan(basePackages = { "com.qualify.student", "com.qualify.concept" })
public class ConfigClass {

    @Bean(name = "teacher")
    public Teacher getTeacher() {
        System.out.println("new bean manual in config class nullify componenet scan");
        return new Teacher();
    }

}