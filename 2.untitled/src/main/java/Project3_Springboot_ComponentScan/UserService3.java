package Project3_Springboot_ComponentScan;

import org.springframework.stereotype.Service;

/**
 * ==================== IMPORTANT DOUBTS & CONCEPTS ====================
 *
 * Q: @Service annotation kya karta hai?
 * A: Yeh Spring ko batata hai ki yeh class ek bean hai
 *    Spring automatically isko scan karke container mein register kar deta
 *    XML ki zarurat nahi - annotation se bean ban jata hai!
 *
 * Q: XML mein vs Annotation mein fark?
 * A: XML: <bean id="service" class="Project1.UserService"/>
 *    Annotation: @Service (automatically bean ID = userService3)
 *
 * Q: Bean ID kya hoga?
 * A: By default: Class name ka camelCase version
 *    UserService3 → userService3
 *    Custom ID chahiye to: @Service("myService")
 *
 * ====================================================================
 */
@Service
public class UserService3 {
  public void getUserName(){
      System.out.println("akash with annotations");
  }
  public void saveUser(){
      System.out.println("user save successfully annotations");
  }
}
