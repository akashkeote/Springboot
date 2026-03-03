package com.episode.second.episode2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.episode.second.episode2.constructor.CarConInj;

/**
 * CONSTRUCTOR INJECTION - BEST PRACTICE ✅
 */
public class Main1ConstructorInjection {

    public static void main(String[] args) {
        System.out.println("\n═══ CONSTRUCTOR INJECTION (Best Practice) ═══\n");

        ApplicationContext context = new ClassPathXmlApplicationContext("config1_constructor.xml");

        CarConInj car = context.getBean("carConstructor", CarConInj.class);

        System.out.println("✅ Engine Type: " + car.getEngine().getType());
        System.out.println("✅ Engine HP: " + car.getEngine().getHorsepower());
        System.out.println("\n✨ BEST PRACTICE: Use constructor for required deps!\n");
    }
}
