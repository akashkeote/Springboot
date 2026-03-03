package com.episode.second.episode2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.episode.second.episode2.field.CarFiInj;

/**
 * FIELD INJECTION - Avoid in Production ❌
 */
public class Main3FieldInjection {

    public static void main(String[] args) {
        System.out.println("\n═══ FIELD INJECTION (Avoid in Production!) ═══\n");

        ApplicationContext context = new ClassPathXmlApplicationContext("config3_field.xml");

        CarFiInj car = context.getBean("carField", CarFiInj.class);

        System.out.println("✅ Engine Type: " + car.getEngine().getType());
        System.out.println("✅ Engine HP: " + car.getEngine().getHorsepower());
        System.out.println("\n⛔ AVOID: Hard to test, use Constructor instead!\n");
    }
}
