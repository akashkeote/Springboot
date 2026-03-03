package com.lifecycle.concepts;

import com.lifecycle.college.Teacher;
import com.lifecycle.qualifier.ColdDrink;
import com.lifecycle.qualifier.Pepsi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.lifecycle" })
public class ConfigClass {

    @Bean(name = "teacher")
    public Teacher getTeacher() {
        return new Teacher();
    }

    @Bean(name = "pepsi2")
    public ColdDrink getColdDrink() {
        return new Pepsi();
    }

}
