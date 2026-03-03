package Project4_Interface_Based_Injection.Service;

/**
 * ==================== NOTIFICATION SERVICE INTERFACE ====================
 *
 * Q: Interface kyun banaya?
 * A: Design Pattern: Strategy Pattern + Dependency Inversion Principle
 *    - High-level code (Controller) depends on abstraction (interface)
 *    - Low-level code (Email/SMS) implement the abstraction
 *    - Easy to add new notification types without changing controller!
 *
 * Q: Interface mein @Service annotation kyun nahi?
 * A: Interface bean nahi ban sakta - only implementations beans bante hain
 *    EmailNotificationService aur SmsNotificationService pe @Service hai
 *
 * Q: Multiple implementations mein se Spring kaise select karta hai?
 * A: @ConditionalOnProperty annotation se!
 *    application.properties mein jo value set ho, wahi bean active hoga
 *
 * ====================================================================
 */
public interface NotificationService {
    void send(String message);
}
