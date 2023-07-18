package tn.esprit.healthcloud.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.ERole;
import tn.esprit.healthcloud.entities.Role;
import tn.esprit.healthcloud.entities.User;
import tn.esprit.healthcloud.repositories.RoleRepository;
import tn.esprit.healthcloud.repositories.UserRepository;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
@AllArgsConstructor
public class UserService implements IUserService{
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    RoleRepository roleRepository;

    @Override
    public User ajouter(User user) {


        encoder.encode(user.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User modifier(User user, long id) {

        User u =userRepository.findById(id).orElse(null);
        if(user.getPassword().isEmpty()) {
            user.getPassword();
            String password = encoder.encode(user.getPassword());
            u.setPassword(password);
        }
        else{
            user.setPassword(u.getPassword());
        }
        user.setStatut(u.getStatut());
        user.setRole(u.getRole());
        user.setJob(u.getJob());
        user.setConfirmPasswordUser(u.getConfirmPasswordUser());
        user.setDateDebutStage(u.getDateDebutStage());
        user.setDateFinStage(u.getDateFinStage());
        user.setResetPasswordToken(u.getResetPasswordToken());



        return userRepository.save(user);
    }

    @Override
    public List<User> afficher() {
        return userRepository.findAll();
    }

    @Override
    public User afficherById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimer(long id) {
        userRepository.deleteById(id);

    }

    @Override
    public void block(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatut(false);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Utilisateur non valide avec l'id" + id);
        }
    }

    @Override
    public void deblock(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatut(true);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Utilisateur non valide avec l'id " + id);
        }
    }
    @Override
    public void updatePassword(String email, String newPassword,String confirmPassword) {
        User u = userRepository.findByEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        String encodedConfirmPassword = passwordEncoder.encode(confirmPassword);
        u.setPassword(encodedPassword);
        u.setConfirmPasswordUser(encodedConfirmPassword);

        u.setResetPasswordToken(null);
        userRepository.save(u);
    }
    @Override
    public void forgotpass(String emailuser) {
        User d = userRepository.findByEmail(emailuser);

        final String username = "hopitalcloud@gmail.com";
        final String password = "wcxyiqtsqwossvvx";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hopitalcloud@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailuser)
            );
            message.setSubject("Rest Your Password");
            message.setText("This a non reply message from HealthCloud\n "
                    +"Dear Client \n"
                    + "Please follow the following link to reser your password: \n" + "http://localhost:4200/#/authentication/reset?email="+emailuser );

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }



    }
    @Override
    public void addwithmail(String emailuser) {
        User d = userRepository.findByEmail(emailuser);

        final String username = "hopitalcloud@gmail.com";
        final String password = "wcxyiqtsqwossvvx";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hopitalcloud@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailuser)
            );
            message.setSubject("Username and Password");
            message.setText("This a non reply message from HealthCloud\n "
                    +"Dear Client \n"
                    + "This is your Username:   " + d.getUsername()+
                    "\n"
                    + "This is your Password:   " + "admin");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }



    }
    @Override
    public List<String> getMailsbyRoles(){
        Role role = roleRepository.getById(2);
        List<User> users=userRepository.getUsersByRole(role);
        List<String> emails=new ArrayList<>();
        for (User u: users
             ) {
            emails.add(u.getEmail());
        }
        return emails;
    }

    @Override
    public List<User> getUsersbyRoles(int idRole){
        Role role = roleRepository.findById(idRole).orElse(null);
        List<User> users=userRepository.getUsersByRole(role);
       return users;
    }


}

