package tn.esprit.healthcloud.services;

import tn.esprit.healthcloud.entities.Donateur;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface IdonateurService {
    List<Donateur> getAllDonateurs();
    Optional<Donateur> getDonateurById(int id);
    Donateur addDonateur(int idBanque,Donateur donateur) throws ChangeSetPersister.NotFoundException;
    Donateur updateDonateur(Donateur donateur);
    void deleteDonateurById(int id);
}
