package tn.esprit.healthcloud.controllers;

import tn.esprit.healthcloud.entities.Logistique;
import tn.esprit.healthcloud.entities.Operation;
import tn.esprit.healthcloud.services.OperationInterface;
import tn.esprit.healthcloud.services.OperationService;
import tn.esprit.healthcloud.services.ILogistiqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logistiques")
public class LogistiqueController {

    private final ILogistiqueService logistiqueService;
    private final OperationInterface operationService;

    public LogistiqueController(ILogistiqueService logistiqueService, OperationService operationService) {
        this.logistiqueService = logistiqueService;
        this.operationService = operationService;
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("")
    public ResponseEntity<Logistique> addLogistique(@RequestBody Logistique logistique) {
        Logistique newLogistique = logistiqueService.addLogistique(logistique);
        return new ResponseEntity<>(newLogistique, HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/{id}")
    public ResponseEntity<Logistique> getLogistiqueById(@PathVariable int id) {
        Logistique logistique = logistiqueService.getLogistiqueById(id);
        if (logistique == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(logistique, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogistique(@PathVariable int id) {
        logistiqueService.deleteLogistique(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("")
    public ResponseEntity<List<Logistique>> getAllLogistiques() {
        List<Logistique> logistiques = logistiqueService.getAllLogistiques();
        return new ResponseEntity<>(logistiques, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("")
    public ResponseEntity<Logistique> updateLogistique(@RequestBody Logistique logistique) {
        Logistique updatedLogistique = logistiqueService.updateLogistique(logistique);
        return new ResponseEntity<>(updatedLogistique, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/type/{typeLogi}")
    public ResponseEntity<List<Logistique>> searchLogistiqueByType(@PathVariable String typeLogi) {
        List<Logistique> logistiques = logistiqueService.searchLogistiqueByType(typeLogi);
        return new ResponseEntity<>(logistiques, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/operation/{idOp}")
    public ResponseEntity<List<Logistique>> getLogistiquesByOperation(@PathVariable int idOp) {
        Operation operation = operationService.getOperationById(idOp);
        if (operation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Logistique> logistiques = logistiqueService.getLogistiquesByOperation(operation);
        return new ResponseEntity<>(logistiques, HttpStatus.OK);
    }
}
