package tn.esprit.healthcloud.services;

import tn.esprit.healthcloud.entities.Role;

import java.util.List;

public interface IRoleService {

    Role ajouter (Role role);
    Role modifier (Role role, int id);
    List<Role> afficher ();
    Role afficherById(int id);
    void supprimer (int id);
}
