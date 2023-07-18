package tn.esprit.healthcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Consultation;
import tn.esprit.healthcloud.services.ConsultationService;
import java.util.List;

@RestController
@RequestMapping("/consultations")
@AllArgsConstructor
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/allConsultation")
    public ResponseEntity<List<Consultation>> getAllConsultations() {
        List<Consultation> consultations = consultationService.getAllConsultations();
        if (consultations.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(consultations);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/addConsultation")
    public ResponseEntity<Consultation> createConsultation(@RequestBody Consultation consultation) {
        Consultation createdConsultation = consultationService.createConsultation(consultation);
        return new ResponseEntity<>(createdConsultation, HttpStatus.CREATED);
    }


    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/{id}")
    public ResponseEntity<Consultation> getConsultationById(@PathVariable("id") Long id) {
        Consultation consultation = consultationService.getConsultationById(id);
        if (consultation != null) {
            return ResponseEntity.ok(consultation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("/{id}")
    public ResponseEntity<Consultation> updateConsultation(@PathVariable("id") Long id, @RequestBody Consultation consultation) {
        Consultation updatedConsultation = consultationService.updateConsultation(id, consultation);
        if (updatedConsultation != null) {
            return ResponseEntity.ok(updatedConsultation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable("id") Long id) {
        consultationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


