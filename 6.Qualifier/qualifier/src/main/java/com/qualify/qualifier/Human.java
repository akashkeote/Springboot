package com.qualify.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Human {

    @Autowired
    // @Qualifier(value = "pepsi")
    ColdDrink coldDrink;

    public void tryColdDrink() {
        coldDrink.drink();
    }

}
