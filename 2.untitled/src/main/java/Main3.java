import Project3_Springboot_ComponentScan.UserController3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * ==================== IMPORTANT DOUBTS & CONCEPTS ====================
 *
 * Q: @SpringBootApplication kya hai?
 * A: Yeh 3 annotations ka combination hai:
 * - @Configuration: Yeh class ek configuration class hai
 * - @EnableAutoConfiguration: Spring Boot auto-config enable karta hai
 * - @ComponentScan: Beans ko scan karta hai current package mein
 *
 * Q: @ComponentScan("Project3") kyun lagaya?
 * A: Main3 class default package mein hai, Project3 package mein nahi
 * Spring ko explicitly batana pada ki Project3 package scan karo
 * Agar Main3 bhi Project3 package mein hota to zarurat nahi thi
 *
 * Q: SpringApplication.run() kya karta hai?
 * A: Yeh Spring Boot application start karta hai:
 * - Spring container create karta hai
 * - Component scanning karta hai
 * - Beans create aur inject karta hai
 * - ApplicationContext return karta hai
 *
 * Q: XML vs Spring Boot mein fark?
 * A: XML: ClassPathXmlApplicationContext("config.xml")
 * Spring Boot: SpringApplication.run(Main3.class, args)
 * Spring Boot automatic configuration karta hai!
 *
 * Q: getBean() ke liye bean ID kyun nahi diya?
 * A: Type-based retrieval use kiya:
 * getBean(UserController3.class) - Type se bean dhoondho
 * getBean("controller3") - ID se bean dhoondho
 * Type-based zyada safe hai
 *
 * ====================================================================
 */
@SpringBootApplication
@ComponentScan("Project3_Springboot_ComponentScan")
public class Main3 {
    public static void main(String[] args) {
        // Spring Boot application start karo aur container get karo
        ApplicationContext container3 = SpringApplication.run(Main3.class, args);

        // Bean get karo - dependency already inject hai
        UserController3 controller3 = container3.getBean(UserController3.class);

        // Note: Constructor mein hi service methods call ho gaye
        // Real application mein yahan se methods call karte
    }
}
