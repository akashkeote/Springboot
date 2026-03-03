package Project4_Interface_Based_Injection.Controller;

import Project4_Interface_Based_Injection.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ==================== IMPORTANT DOUBTS & CONCEPTS ====================
 *
 * Q: Constructor mein parameter 's' kyun hai aur field 'service' kyun?
 * A: Parameter naam kuch bhi ho sakta (s, srv, notificationService)
 *    Important: Parameter ko field mein ASSIGN karna zaroori hai!
 *    WRONG: this.service = service (parameter 's' ko assign nahi kiya)
 *    RIGHT: this.service = s (parameter assign ho gaya)
 *
 * Q: @Autowired kaise kaam karta hai?
 * A: Spring automatically NotificationService implementation inject karta hai
 *    - application.properties check karta: notify.type=email
 *    - EmailNotificationService inject kar dega (sms nahi)
 *
 * Q: Interface NotificationService inject kaise ho raha hai?
 * A: Spring runtime pe implementation dhoondhta hai (@ConditionalOnProperty se)
 *    notify.type=email → EmailNotificationService
 *    notify.type=sms → SmsNotificationService
 *
 * ====================================================================
 */
@Component
public class NotificationController {
    private final NotificationService service;

    @Autowired
    public NotificationController(NotificationService s){
        this.service = s;  // ✅ FIXED: Parameter 's' ko assign kiya
    }

    public void notifyUser(){
        service.send("hi choose kiya bhdwe tune");
    }
}
