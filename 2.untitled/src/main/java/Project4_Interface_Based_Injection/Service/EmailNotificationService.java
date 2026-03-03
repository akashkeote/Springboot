package Project4_Interface_Based_Injection.Service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * ==================== CONDITIONAL BEAN - EMAIL ====================
 *
 * Q: @ConditionalOnProperty kaise kaam karta hai?
 * A: application.properties file check karta hai:
 *    notify.type=email → Yeh bean active ho jayega
 *    notify.type=sms → Yeh bean inactive rahega
 *
 * Q: Multiple implementations hone pe Spring confusion mein kyun nahi hota?
 * A: @ConditionalOnProperty filter ka kaam karta hai
 *    Only ONE bean active hota hai based on property value
 *    No ambiguity - clear choice!
 *
 * Q: Interface implement karne ka fayda?
 * A: Polymorphism! Controller mein NotificationService type use karte hain
 *    Runtime pe Email ya SMS implementation inject hota hai
 *    Loose coupling - Easy to extend!
 *
 * ====================================================================
 */
@Service
@ConditionalOnProperty(name="notify.type", havingValue = "email")
public class EmailNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

