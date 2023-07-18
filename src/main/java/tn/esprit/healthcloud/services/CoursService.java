package tn.esprit.healthcloud.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.Cours;
import tn.esprit.healthcloud.entities.User;
import tn.esprit.healthcloud.repositories.CoursRepository;
import tn.esprit.healthcloud.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CoursService implements ICoursService {
    private CoursRepository coursRepository;
    private UserRepository userRepository;

    @Override
    public List <Cours> getAllCours(){
        return coursRepository.findAll();};
    @Override
    public Cours getCoursById(int id) {
        return coursRepository.findById(id).orElse(null);
    }

    @Override
    public Cours saveCours(Cours cours) {
        return coursRepository.save(cours);
    }

    @Override
    public void deleteCours(int id) {
        coursRepository.deleteById(id);
    }

    @Override
    public List<Cours> getCoursByDoctorId(long doctorId) {
        User doctor = userRepository.findById(doctorId).orElse(null);
        if (doctor != null) {
            return coursRepository.findByDoctor(doctor);
        }
        return null;
    }

    @Override
    public List<Cours> getCoursByStagiaireId(long stagiaireId) {
        User stagiaire = userRepository.findById(stagiaireId).orElse(null);
        if (stagiaire != null) {
            return coursRepository.findByStagiaires(stagiaire);
        }
        return null;
    }
    @Override
    public void addStagiaireToCours(int coursId, long stagiaireId) {
        Cours cours = coursRepository.findById(coursId).orElse(null);
        User stagiaire = userRepository.findById(stagiaireId).orElse(null);
        if (cours != null && stagiaire != null) {
            cours.getStagiaires().add(stagiaire);
            coursRepository.save(cours);
        }
    }
    @Override
    public void removeStagiaireFromCours(int coursId, long stagiaireId) {
        Cours cours = coursRepository.findById(coursId).orElse(null);
        User stagiaire = userRepository.findById(stagiaireId).orElse(null);
        if (cours != null && stagiaire != null) {
            cours.getStagiaires().remove(stagiaire);
            coursRepository.save(cours);
        }
    }
}
