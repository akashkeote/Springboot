package com.fifth.more_on_config.configclass_more.concepts;

import org.springframework.stereotype.Component;

@Component("engine1")
public class Engine {
    public Engine() {
        System.out.println("creating engine object");
    }
    public void startEngine() {
        System.out.println("engine started...");
    }
}