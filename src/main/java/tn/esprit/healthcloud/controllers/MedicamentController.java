package tn.esprit.healthcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Medicament;
import tn.esprit.healthcloud.repositories.MedicamentRepository;
import tn.esprit.healthcloud.services.IMedicamentService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/Medicament")
public class MedicamentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);
    @Autowired
    private MedicamentRepository medicamentRepository;
    @Autowired
    IMedicamentService medicamentService;
    @PostMapping("/add-medicament")
    public Medicament addMedicament(@RequestBody Medicament m) {
        Medicament medicament = medicamentService.addMedicament(m);
        return medicament;
    }
    @DeleteMapping("/delete-medicament/{id-medicament}")
    public void deletePharmacie(@PathVariable("id-medicament") int id)
    {
        medicamentService.deleteMedicament(id);
    }

    @GetMapping("/getMedicament/{id}")
    public Medicament getPharmacieById(@PathVariable int id)
    {
        return medicamentService.getMedicament(id);
    }
    @GetMapping("/getAllMedicament")
    public List<Medicament> getAllMedicament()
    {
        return medicamentService.getAllMedicament();
    }
    @PutMapping("/updatePharmacie/{idMedicament}")
    public Medicament updatePharmacie(@PathVariable("idMedicament") int id_medicament, @RequestBody Medicament medicament)
    {
        return medicamentService.updateMedicament(medicament,id_medicament);
    }
    @PutMapping("/{id}/stock")
    public ResponseEntity<?> updateStock(@PathVariable(value = "id") int id,
                                         @RequestParam(value = "quantity") long quantity) {
        Optional<Medicament> medicament = medicamentRepository.findById(id);
        if (medicament.isPresent()) {
            Medicament m = medicament.get();
            if (m.getStock() >= quantity) {
                m.setStock(m.getStock() - quantity);
                medicamentRepository.save(m);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body("Insufficient stock for the requested quantity");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update-stock")
    public ResponseEntity<?> updateStock(@RequestBody List<Medicament> medicaments) {
        List<String> errors = new ArrayList<>();
        for (Medicament mq : medicaments) {
            Optional<Medicament> medicament = medicamentRepository.findById(mq.getId());
            if (medicament.isPresent()) {
                Medicament m = medicament.get();
                if (m.getStock() >= mq.getStock()) {
                    m.setStock(mq.getStock());
                    medicamentRepository.save(m);
                } else {
                    errors.add(String.format("Insufficient stock for medication '%s' (id=%d). Available stock: %d, requested quantity: %d",
                            m.getNom(), m.getId(), m.getStock(), mq.getStock()));
                }
            } else {
                errors.add(String.format("Medication with id=%d not found", mq.getId()));
            }
        }
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        } else {
            return ResponseEntity.ok().build();
        }
    }
    @GetMapping("/getcounttypes")
    public int getCountTypes()
    {
        return medicamentRepository.countByNom();
    }

}
