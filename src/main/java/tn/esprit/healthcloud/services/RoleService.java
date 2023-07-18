package tn.esprit.healthcloud.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.Role;
import tn.esprit.healthcloud.repositories.RoleRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class RoleService implements IRoleService{
    RoleRepository roleRepository;


    @Override
    public Role ajouter(Role role) {
        return roleRepository.save(role);  }

    @Override
    public Role modifier(Role role, int id) {
        roleRepository.findById(id).orElse(null);
        return roleRepository.save(role);
    }

    @Override
    public List<Role> afficher() {
        return roleRepository.findAll();
    }

    @Override
    public Role afficherById(int id) {
        return  roleRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimer(int id) {
        roleRepository.deleteById(id);

    }
}
