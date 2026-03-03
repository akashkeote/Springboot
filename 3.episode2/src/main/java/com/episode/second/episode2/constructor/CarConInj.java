package com.episode.second.episode2.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CONSTRUCTOR INJECTION Example
 * 
 * Dependency is injected through constructor parameter using @Autowired
 * 
 * PROS:
 * ✅ Makes immutable objects (final keyword possible)
 * ✅ Ensures dependency is present (fail fast)
 * ✅ Easy to test (can pass mock in constructor)
 * ✅ Clear dependencies at glance
 * ✅ Good for required dependencies
 * ✅ Detects circular dependencies early
 * 
 * CONS:
 * ❌ More verbose code
 * ❌ Complex constructors for many dependencies
 * ❌ Can't use @Autowired on constructor in Spring < 4.3
 * 
 * RECOMMENDATION: ✨ BEST PRACTICE - Use this!
 */
@Component("carConstructor")
public class CarConInj {

    private final EngineConInj engine;

    @Autowired
    public CarConInj(EngineConInj engine) {
     
        this.engine = engine;
    }

    public EngineConInj getEngine() {
        return engine;
    }

    @Override
    public String toString() {
        return "CarConInj [engine=" + engine + "]";
    }
}
