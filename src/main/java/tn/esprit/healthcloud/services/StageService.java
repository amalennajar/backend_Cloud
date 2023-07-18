package tn.esprit.healthcloud.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.Stage;
import tn.esprit.healthcloud.repositories.StageRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class StageService implements IStageService {
    private final StageRepository stageRepository;

    public Stage saveStage(Stage stage) {
        return stageRepository.save(stage);
    }

    public Stage getStageById(Long id) {
        return stageRepository.findById(id).orElse(null);
    }

    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    public List<Stage> getStageByUserId(Long idUser){return stageRepository.getStageAllByStagiaireIdUser(idUser);}

    public void deleteStageById(Long id) {
        stageRepository.deleteById(id);
    }
}
