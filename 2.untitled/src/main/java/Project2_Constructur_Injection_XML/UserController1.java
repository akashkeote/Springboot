package Project2_Constructur_Injection_XML;

/**
 * ==================== CONSTRUCTOR INJECTION - METHOD 2 ====================
 *
 * Q: Constructor injection aur setter injection mein kya difference hai?
 * A: Constructor Injection:
 * - Dependency object creation time inject hoti hai
 * - Field ko final banaya ja sakta hai (immutable)
 * - Mandatory dependency ke liye best practice
 *
 * Setter Injection:
 * - Dependency object creation ke baad inject hoti hai
 * - Optional dependencies ke liye use karte hain
 * - Field final nahi ho sakti
 *
 * Q: XML mein <constructor-arg> kaise kaam karta hai?
 * A: config1.xml mein define hai:
 * <constructor-arg ref="service1"/>
 * Spring automatically constructor call karta hai aur service1 bean pass karta
 * hai
 *
 * Q: Constructor mein business logic call karna sahi hai?
 * A: Production code mein nahi! Constructor sirf dependency set kare
 * Yahan demo ke liye methods call kiye hain
 * Best Practice: Alag method banao jisme business logic ho
 *
 * ====================================================================
 */
public class UserController1 {
    private final UserService1 service1;

    public UserController1(UserService1 service1) {
        this.service1 = service1;
        service1.getUserName();
        service1.saveUser();
    }
}
