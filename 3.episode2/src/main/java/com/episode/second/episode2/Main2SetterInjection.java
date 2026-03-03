package com.episode.second.episode2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.episode.second.episode2.setter.CarSeInj;

/**
 * SETTER INJECTION - For Optional Dependencies ⚠️
 */
public class Main2SetterInjection {

    public static void main(String[] args) {
        System.out.println("\n═══ SETTER INJECTION (Optional Dependencies) ═══\n");

        ApplicationContext context = new ClassPathXmlApplicationContext("config2_setter.xml");

        CarSeInj car = context.getBean("carSetter", CarSeInj.class);

        System.out.println("✅ Engine Type: " + car.getEngine().getType());
        System.out.println("✅ Engine HP: " + car.getEngine().getHorsepower());
        System.out.println("\n⚠️  Use setter for OPTIONAL dependencies only!\n");

    }
}
