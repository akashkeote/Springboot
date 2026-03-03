import Project4_Interface_Based_Injection.Controller.NotificationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * ==================== IMPORTANT DOUBTS & CONCEPTS ====================
 *
 * Q: Yeh Project3 se kaise alag hai?
 * A: Project3: Single implementation (UserService3)
 * Project4: Multiple implementations (EmailNotificationService,
 * SmsNotificationService)
 * 
 * @ConditionalOnProperty se Spring decide karta hai konsa bean use karna hai
 *
 *                        Q: application.properties ka role kya hai?
 *                        A: notify.type=email se Spring ko pata chalta:
 *                        - EmailNotificationService bean create karo
 *                        - SmsNotificationService bean skip karo
 *                        Property change karke behavior change kar sakte ho!
 *
 *                        Q: Interface injection kaise kaam karta hai?
 *                        A: NotificationController mein NotificationService
 *                        inject ho raha hai (interface)
 *                        Spring runtime pe actual implementation inject karta
 *                        hai
 *                        Yeh Dependency Inversion Principle hai (SOLID)
 *
 *                        ====================================================================
 */
@SpringBootApplication
@ComponentScan("Project4_Interface_Based_Injection")
public class Main4 {
    public static void main(String[] args) {
        // Spring Boot application start karo
        ApplicationContext container3 = SpringApplication.run(Main4.class, args);

        // NotificationController bean get karo
        NotificationController controller3 = container3.getBean(NotificationController.class);

        // Notification send karo (email ya sms - property se decide hoga)
        controller3.notifyUser();
    }
}
