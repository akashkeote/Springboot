package Project3_Springboot_ComponentScan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * ==================== IMPORTANT DOUBTS & CONCEPTS ====================
 *
 * Q: Ab tak annotations kyun nahi lagate the?
 * A: Kyunki Project1 aur Project2 mein XML-based approach use kiya
 *    - XML approach: config.xml mein beans define karte hain
 *    - Annotation approach: @Service, @RestController se beans bante hain
 *    Yeh do alag tarike hain - ek sath use nahi karte!
 *
 * Q: @RestController kya hai?
 * A: Yeh annotation Spring ko batata hai ki:
 *    - Yeh class ek Spring Bean hai
 *    - Yeh REST API controller hai
 *    Internally @Component + @ResponseBody hai
 *
 * Q: @Autowired kaise kaam karta hai?
 * A: Spring automatically dependency inject kar deta hai
 *    - UserService3 ka bean dhoondhta hai (@Service annotation se)
 *    - Constructor mein inject kar deta hai
 *    - XML ki zarurat nahi!
 *
 * Q: Constructor mein method calls kyun kar rahe hain?
 * A: Just for demo - dependency inject ho gayi hai dikha rahe hain
 *    Real application mein yeh separate method se call karte
 *
 * Q: Import automatic kyun add hote hain ab?
 * A: Maven ne Spring dependencies download kar li hain
 *    IntelliJ ko pata chal gaya ki classes exist karti hain
 *    Alt+Enter se automatic import ho jata hai
 *
 * ====================================================================
 */
@RestController
public class UserController3 {
    private UserService3 service3;

    @Autowired
    public UserController3(UserService3 service3){
        this.service3=service3;
        service3.getUserName();
        service3.saveUser();
    }

}
