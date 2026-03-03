package com.episode.second.episode2.classes_mine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component("car")
public class Car {
    //@Autowired //field level pe autowiring karne ke liye use hota hai but isme encapsulation ka violation hota hai isliye setter method se autowiring karna better hota hai
    private Engine engine;

    public Car() {
        //best way to do autowiring is by constructor injection but isme circular dependency ka problem hota hai isliye setter method se autowiring karna better hota hai
        //by default constructor injection hota hai jab hum @Autowired use karte hai field level pe but isme encapsulation ka violation hota hai isliye setter method se autowiring karna better hota hai
        //bas ek consrtuctor hona chahhiye par 
        //object issse bana and then setter se inject hui dependecy
    }

    public Engine getEngine() {
        return engine;
    }
  //setter method se autowiring hui yaha 
  //best method to do autowiring is by setter injection
    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

}
