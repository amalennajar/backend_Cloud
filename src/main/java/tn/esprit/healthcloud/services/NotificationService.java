package tn.esprit.healthcloud.services;

import org.springframework.stereotype.Service;

@Service
public class NotificationService implements InotificationService {




    @Override
    public void sendNotification(String message) {
        // Code to send notification
        System.out.println("Notification sent: " + message);
    }


}
