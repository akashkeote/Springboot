package com.episode.second.episode2.classes;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component("engine")
public class Engine {
    private String type;

    public Engine() {
        type = "V8";
    }

    public Engine(String type, int horsepower) {
        this.type = type;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
