package tn.esprit.healthcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Role;
import tn.esprit.healthcloud.services.IRoleService;

import java.util.List;
@RestController
@AllArgsConstructor
public class RoleController {
    IRoleService iRoleService;


    @PostMapping("/Role")
    Role addRole(@RequestBody Role role){
        return iRoleService.ajouter(role);
    }
    @PutMapping("/Role/{id}")
    Role modifierRole(@RequestBody Role role, @PathVariable int id){
        return iRoleService.modifier(role,id);
    }
    @GetMapping("/Role")
    List<Role> afficherRole(){
        return iRoleService.afficher();
    }
    @GetMapping("/Role/{id}")
    Role afficherRoleById(@PathVariable int id){
        return iRoleService.afficherById(id);
    }
    @DeleteMapping("/Role/{id}")
    void supprimerRole(@PathVariable int id){
        iRoleService.supprimer(id);
    }
}
