package com.episode.second.episode2.constructor;

import org.springframework.stereotype.Component;

/**
 * Engine class for Constructor Injection example
 */
@Component("engineConstructor")
public class EngineConInj {
    private String type;
    private int horsepower;

    public EngineConInj() {
        this.type = "V8 Turbo";
        this.horsepower = 500;
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
