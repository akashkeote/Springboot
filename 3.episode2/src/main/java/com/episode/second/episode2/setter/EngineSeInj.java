package com.episode.second.episode2.setter;

import org.springframework.stereotype.Component;

/**
 * Engine class for Setter Injection example
 */
@Component("engineSetter")
public class EngineSeInj {
    private String type;
    private int horsepower;

    public EngineSeInj() {
        this.type = "V8";
        this.horsepower = 300;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    @Override
    public String toString() {
        return "Engine [type=" + type + ", horsepower=" + horsepower + "]";
    }
}
