package com.fifth.more_on_config.configclass_more.config;

import com.fifth.more_on_config.configclass_more.student.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.fifth.more_on_config.configclass_more.student" ,"com.fifth.more_on_config.configclass_more.concepts"})
public class ConfigClass {

    @Bean(name = "teacher")
    public Teacher getTeacher() {
        System.out.println("new bean manual in config class nullify componenet scan");
        return new Teacher();
    }

}