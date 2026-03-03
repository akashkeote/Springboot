package Project1_Setter_Injection_XML;

/**
 * ==================== IMPORTANT DOUBTS & CONCEPTS ====================
 * 
 * Q: Yahan @Service annotation kyun nahi hai?
 * A: Kyunki hum XML-based configuration use kar rahe hain!
 *    - XML approach: Annotations nahi chahiye, sab config.xml mein
 *    - Annotation approach: @Service lagana padta, XML ki zarurat nahi
 *    Dono tarike alag hain - ek sath use nahi karte!
 * 
 * Q: Yeh bean kaise ban raha hai?
 * A: config.xml file mein define kiya hai:
 *    <bean id="service" class="Project1.UserService">
 *    Spring XML read karke automatically bean banata hai
 * 
 * ====================================================================
 */
public class UserService {
    
    // Business logic methods
    public void getUserName() {
        System.out.println("User name is: John Doe");
    }

    public void saveUser() {
        System.out.println("User saved successfully");
    }
}
