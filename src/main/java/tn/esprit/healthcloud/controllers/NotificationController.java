package tn.esprit.healthcloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.healthcloud.entities.NotificationMessage;
import tn.esprit.healthcloud.services.FirebaseMessagingService;

@RestController

public class NotificationController {

    @Autowired
    FirebaseMessagingService firebaseMessagingService;

    @PostMapping("/notification")
    public String sendNotificationByToken(@RequestBody NotificationMessage notificationMessage, @RequestParam Long userId){
        return firebaseMessagingService.sendNotificationByToken(notificationMessage,userId );
    }

}
