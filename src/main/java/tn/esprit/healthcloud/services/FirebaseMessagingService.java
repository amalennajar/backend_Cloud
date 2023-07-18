package tn.esprit.healthcloud.services;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.NotificationMessage;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class FirebaseMessagingService {
    @Autowired
    private FirebaseMessaging firebaseMessaging;
@Autowired
    private FBTService fbtService;
    public String sendNotificationByToken(NotificationMessage notificationMessage, long userId){

        notificationMessage.setRecipientToken(fbtService.getDeviceToken(userId));

        Notification notification = new Notification(
                notificationMessage.getTitle(),
                notificationMessage.getBody());
        Message message = Message
                .builder()
                .setToken(notificationMessage.getRecipientToken())
                .setNotification(notification)
                .build();
        try{
            firebaseMessaging.send(message);
            return "success";
        }catch(FirebaseMessagingException e){
            e.printStackTrace();
            return "error sending";
        }
    }

}
