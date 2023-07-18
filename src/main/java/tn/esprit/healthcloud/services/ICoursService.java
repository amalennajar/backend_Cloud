package tn.esprit.healthcloud.services;

import tn.esprit.healthcloud.entities.Cours;
import tn.esprit.healthcloud.entities.User;

import java.util.List;

public interface ICoursService {

    List <Cours> getAllCours();
    Cours getCoursById(int id);

    Cours saveCours(Cours cours);

    void deleteCours(int id);
    // You can add additional service methods here, if needed
    List<Cours> getCoursByDoctorId(long doctorId);
    List<Cours> getCoursByStagiaireId(long stagiaireId);
    void addStagiaireToCours(int coursId, long stagiaireId);
    void removeStagiaireFromCours(int coursId, long stagiaireId);
}
