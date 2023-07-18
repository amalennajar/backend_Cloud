package tn.esprit.healthcloud.services;

import tn.esprit.healthcloud.entities.Stage;
import java.util.List;

public interface IStageService {
    public Stage saveStage(Stage stage);
    public Stage getStageById(Long id);
    public List<Stage> getAllStages();
    public void deleteStageById(Long id);
    public List <Stage> getStageByUserId(Long idUser);
}
