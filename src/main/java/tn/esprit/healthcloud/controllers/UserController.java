package tn.esprit.healthcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import tn.esprit.healthcloud.config.request.LoginRequest;
import tn.esprit.healthcloud.config.request.ResetRequest;
import tn.esprit.healthcloud.entities.User;

import tn.esprit.healthcloud.services.IUserService;

import java.util.List;
@RestController
@AllArgsConstructor
public class UserController {
    IUserService iUserService;
    PasswordEncoder encoder;

    @PostMapping("/User")
    User addAdmin(@RequestBody User user){
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        user.setStatut(true);

        iUserService.ajouter(user);

        iUserService.addwithmail(user.getEmail());
        return user;
    }
    @PutMapping("/User/{id}")
    User modifierAdmin(@RequestBody User user, @PathVariable long id){
        return iUserService.modifier(user,id);
    }
    @GetMapping("/User")
    List<User> afficherAdmin(){
        return iUserService.afficher();
    }
    @GetMapping("/User/{id}")
    User afficherAdminById(@PathVariable long id){
        return iUserService.afficherById(id);
    }
    @DeleteMapping("/User/{id}")
    void supprimeradmin(@PathVariable long id){
        iUserService.supprimer(id);
    }

    @DeleteMapping("/User/block/{id}")
    void blockerAdmin(@PathVariable long id ){
        iUserService.block(id);
    }
    @DeleteMapping("/User/deblock/{id}")
    void deblockerAdmin(@PathVariable long id ){
        iUserService.deblock(id);
    }
    @GetMapping("/User/role")
    List<String> getmaibyrole(){
        return iUserService.getMailsbyRoles();
    }
    @GetMapping("/User/role/{r}")
    List<User> getUsersbyRoles(@PathVariable int r){
        return iUserService.getUsersbyRoles(r);
    }

}
