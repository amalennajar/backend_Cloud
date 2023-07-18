package tn.esprit.healthcloud.services;

import javax.mail.MessagingException;
import javax.mail.util.ByteArrayDataSource;

public interface IEmailSample {
    void sendEmailSample(String to, String subject, String body) throws MessagingException;
}
