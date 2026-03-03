package Project1_Setter_Injection_XML;

/**
 * ==================== IMPORTANT DOUBTS & CONCEPTS ====================
 * 
 * Q: @RestController annotation kyun nahi hai?
 * A: XML-based configuration mein annotations nahi use karte!
 * Yeh simple POJO class hai jo XML se bean ban jayega
 * 
 * Q: Setter method ka naam "setUserService" hi kyun rakha?
 * A: JavaBeans naming convention follow karna padta hai:
 * - Field: service
 * - Setter: setUserService (meaningful naam)
 * - XML property name: userService (setter ka naam minus "set")
 * 
 * Q: Spring kaise inject karega dependency?
 * A: config.xml mein <property name="userService" ref="service"/> hai
 * Spring automatically setUserService() method call karega
 * Aur "service" bean as parameter pass karega
 * 
 * ====================================================================
 */
public class UserController {

    // Dependency - UserService ko inject karna hai
    private UserService service;

    /**
     * SETTER INJECTION METHOD
     * 
     * Important Points:
     * 1. Method naam: setUserService (not setService!)
     * 2. XML property name: userService (setter minus "set", lowercase first)
     * 3. Spring automatically call karega yeh method
     * 4. Dependency inject ho jayegi
     * 
     * Agar method naam setMyService() hota:
     * - XML mein <property name="myService" ref="service"/> likhna padta
     */
    public void setUserService(UserService service) {
        this.service = service;
    }

    // Business methods jo injected service use karte hain
    public void getUserName() {
        service.getUserName(); // DI kaam kar raha - service null nahi hai!
    }

    public void saveUser() {
        service.saveUser(); // Dependency automatically inject ho gayi
    }

}