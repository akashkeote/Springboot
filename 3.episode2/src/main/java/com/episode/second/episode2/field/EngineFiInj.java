package com.episode.second.episode2.field;

import org.springframework.stereotype.Component;

/**
 * Engine class for Field Injection example
 */
@Component("engineField")
public class EngineFiInj {
    private String type;
    private int horsepower;

    public EngineFiInj() {
        this.type = "V6";
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
