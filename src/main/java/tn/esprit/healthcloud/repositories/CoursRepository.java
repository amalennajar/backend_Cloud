package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.healthcloud.entities.Cours;
import tn.esprit.healthcloud.entities.User;

import java.util.List;

@Repository

public interface CoursRepository extends JpaRepository<Cours, Integer> {
    List<Cours> findByDoctor(User doctor);
    List<Cours> findByStagiaires(User stagiaire);

}
