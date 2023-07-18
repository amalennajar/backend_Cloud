package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.healthcloud.entities.Stage;

import java.util.List;

// StageRepository.java
@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
    List<Stage> getStageAllByStagiaireIdUser(Long idUser);
}

