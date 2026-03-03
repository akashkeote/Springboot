package com.episode.second.episode2.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FIELD INJECTION Example
 * 
 * Dependency is injected directly into the field using @Autowired
 * 
 * PROS:
 * ✅ Concise and clean code
 * ✅ Easy to read
 * ✅ Minimal boilerplate
 * 
 * CONS:
 * ❌ Immutability not possible (field must be non-final)
 * ❌ Hard to test (need reflection to set field)
 * ❌ Circular dependency issues
 * ❌ NullPointerException risk if bean not initialized
 * 
 * WHEN TO USE: Simple cases, not recommended for production
 */
@Component("carField")
public class CarFiInj {

    @Autowired
    private EngineFiInj engine;

    public CarFiInj() {
        System.out.println("CarFiInj constructor called");
    }

    public EngineFiInj getEngine() {
        return engine;
    }

    @Override
    public String toString() {
        return "CarFiInj [engine=" + engine + "]";
    }
}
