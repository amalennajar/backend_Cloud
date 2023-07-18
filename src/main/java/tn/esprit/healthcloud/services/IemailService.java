package tn.esprit.healthcloud.services;

import com.sun.istack.ByteArrayDataSource;

import javax.mail.MessagingException;

public interface IemailService {
     void sendEmailqr(String to, String subject, String body, ByteArrayDataSource attachment, String attachmentName) throws MessagingException;

}
