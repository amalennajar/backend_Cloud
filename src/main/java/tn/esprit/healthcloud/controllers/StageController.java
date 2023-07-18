package tn.esprit.healthcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Stage;
import tn.esprit.healthcloud.services.StageService;

import java.util.List;

@RestController
@RequestMapping("/api/stage")
@AllArgsConstructor
public class StageController {
    private final StageService stageService;

    @PostMapping("")
    public ResponseEntity<Stage> saveStage(@RequestBody Stage stage) {
        Stage savedStage = stageService.saveStage(stage);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStage);
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<Stage>> getStageByUserId(@PathVariable Long idUser) {
        List<Stage> stageOptional = stageService.getStageByUserId(idUser);
        return ResponseEntity.status(HttpStatus.FOUND).body(stageOptional) ;
    }


    @GetMapping("")
    public ResponseEntity<List<Stage>> getAllStages() {
        List<Stage> stages = stageService.getAllStages();
        return ResponseEntity.status(HttpStatus.OK).body(stages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stage> getStageById(@PathVariable Long id) {
        Stage stageOptional = stageService.getStageById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(stageOptional) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStageById(@PathVariable Long id) {
        stageService.deleteStageById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
