import Project1_Setter_Injection_XML.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ==================== IMPORTANT DOUBTS & CONCEPTS ====================
 * 
 * Q: Import automatic kyun nahi huye?
 * A: Jab tak Spring dependency download nahi hui, IntelliJ ko pata nahi
 * ki ApplicationContext class exist karti hai
 * Solution:
 * 1. pom.xml mein spring-context dependency add karo
 * 2. Maven reload karo
 * 3. Alt+Enter press karke automatic import use karo
 * 
 * Q: ApplicationContext kya hai? Container kyun?
 * A: ApplicationContext = Spring ka IoC (Inversion of Control) Container
 * Iska kaam:
 * - XML/Config file read karna
 * - Beans create karna (objects banana)
 * - Dependencies inject karna
 * - Bean lifecycle manage karna
 * Variable naam "container" rakha kyunki yeh beans ka container hai!
 * (Tum ctx, context, factory bhi rakh sakte ho)
 * 
 * Q: ClassPathXmlApplicationContext kya hai?
 * A: Yeh ApplicationContext ka implementation hai jo:
 * - Classpath se XML file read karta hai
 * - "config.xml" resources folder mein hai
 * - Automatically beans create aur inject karta hai
 * 
 * Q: UserController.class kyun likha?
 * A: Type Safety ke liye!
 * Without .class: UserController c = (UserController)
 * container.getBean("controller");
 * With .class: UserController c = container.getBean("controller",
 * UserController.class);
 * .class = Java reflection - automatic casting, no errors!
 * 
 * Q: Bean id "controller" kahan se aaya?
 * A: config.xml mein <bean id="controller" ...> define kiya hai
 * Same id use karna padta hai getBean() mein
 * 
 * ====================================================================
 */
public class Main {
    public static void main(String[] args) {
        // Step 1: Spring Container initialize karo aur XML load karo
        ApplicationContext container = new ClassPathXmlApplicationContext("config.xml");

        // Step 2: Controller bean get karo (dependency already injected hai)
        // "controller" = bean id from config.xml
        // UserController.class = Type safety ke liye
        UserController controller = container.getBean("controller", UserController.class);

        // Step 3: Test karo - Dependency Injection working hai!
        // controller ke andar service automatically inject ho chuka hai
        controller.getUserName(); // Output: User name is: John Doe
        controller.saveUser(); // Output: User saved successfully

        // Success! Setter Injection successfully kaam kar raha hai! 🎉
    }

    public static class Main2 {
    }
}