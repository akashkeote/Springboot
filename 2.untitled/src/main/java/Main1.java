import Project2_Constructur_Injection_XML.UserController1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ==================== CONSTRUCTOR INJECTION MAIN CLASS ====================
 *
 * Q: config1.xml mein kya different hai config.xml se?
 * A: Injection technique alag hai:
 * - config.xml: <property> tag (Setter Injection)
 * - config1.xml: <constructor-arg> tag (Constructor Injection)
 *
 * Q: Constructor injection ka advantage kya hai?
 * A: - Mandatory dependencies ke liye best practice
 * - Field ko final bana sakte ho (immutable)
 * - Object creation time hi dependency inject hoti hai
 * - Thread-safe by default
 *
 * Q: getBean() call ke baad kuch kyun nahi kiya?
 * A: Constructor mein hi service methods call ho gaye!
 * Constructor injection mein dependency set hote hi logic execute ho gaya
 * Output console mein directly print ho gaya
 *
 * ====================================================================
 */
public class Main1 {
    public static void main(String[] args) {
        // Spring Container with XML configuration (Constructor Injection)
        ApplicationContext container1 = new ClassPathXmlApplicationContext("config1.xml");

        // Bean retrieval - constructor already called with dependency injected
        UserController1 controller1 = container1.getBean("controller1", UserController1.class);

        // Output already printed from constructor!
        // Constructor mein service.getUserName() aur service.saveUser() call ho gaye
    }
}
