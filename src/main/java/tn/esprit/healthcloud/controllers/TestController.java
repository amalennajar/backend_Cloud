package tn.esprit.healthcloud.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.healthcloud.config.CustomUserDetails;

@RestController
@RequestMapping
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/utilisateur")
    @PreAuthorize("hasRole('CHIRURGIEN') or hasRole('ADMIN') or hasRole('MEDECIN') or hasRole('STAGIAIRE') or hasRole('INFIRMIER')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/med")
    @PreAuthorize("hasRole('MODERATOR')")
    public String medecinAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
    @GetMapping("/chirur")
    @PreAuthorize("hasRole('CHIRURGIEN')")
    public String chirurgienAccess() {
        return "Admin Board.";
    }
    @GetMapping("/infi")
    @PreAuthorize("hasRole('INFIRMIER')")
    public String infirmierAccess() {
        return "Admin Board.";
    }
    @GetMapping("/stag")
    @PreAuthorize("hasRole('STAGIAIRE')")
    public String stagiaireAccess() {
        return "Admin Board.";
    }
    @GetMapping("/currentuser")
    public String currentuser(){
        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String current="id: "+userDetails.getId()
                    +" passwpord: "+userDetails.getPassword()
                    +" username: "+userDetails.getUsername()
                    +" email: "+userDetails.getEmail()
                    +" role: "+userDetails.getAuthorities();
   return current;



    }
}

