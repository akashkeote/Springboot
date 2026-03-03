package Project4_Interface_Based_Injection.Service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name="notify.type",havingValue = "sms")
public class SmsNotificationService implements NotificationService {


    @Override
    public void send(String message) {
        System.out.println("Sms :"+" "+message);
    }
}
