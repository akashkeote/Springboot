package Project2_Constructur_Injection_XML;

/**
 * ==================== SERVICE CLASS - METHOD 2 ====================
 *
 * Q: Yahan bhi @Service annotation kyun nahi hai?
 * A: Kyunki Method 2 mein XML-based configuration use kar rahe hain
 *    config1.xml mein bean define kiya hai:
 *    <bean id="service1" class="Project2.UserService1"/>
 *
 * Q: Method 1 (Project1) aur Method 2 (Project2) mein kya difference hai?
 * A: Method 1: Setter Injection (<property> tag)
 *    Method 2: Constructor Injection (<constructor-arg> tag)
 *    Dono XML-based hain, bas injection technique alag hai
 *
 * ====================================================================
 */
public class UserService1 {
    public void getUserName(){
        System.out.println("akash is username");
    }

    public void saveUser(){
        System.out.println("User akash is saved");
    }
}
