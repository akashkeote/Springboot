package com.episode.second.episode2.setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.episode.second.episode2.field.EngineFiInj;

/**
 * SETTER INJECTION Example
 * 
 * Dependency is injected via setter method using @Autowired
 */
@Component("carSetter")
public class CarSeInj {
    private EngineFiInj engine;

    public CarSeInj() {
        System.out.println("CarSeInj constructor called");
    }

    public EngineFiInj getEngine() {
        return engine;
    }

    @Autowired
    public void setEngine(EngineFiInj engine) {
        System.out.println("CarSeInj.setEngine() called");
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "CarSeInj [engine=" + engine + "]";
    }
}
