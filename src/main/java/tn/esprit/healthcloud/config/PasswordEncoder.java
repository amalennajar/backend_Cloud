package tn.esprit.healthcloud.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static  void main(String[] args){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "bringa";
        String encodedPaswword = encoder.encode(rawPassword);

        System.out.println(encodedPaswword);
    }
}
