package tn.esprit.healthcloud.controllers;

import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.DossierMedical;
import tn.esprit.healthcloud.services.IDossierMService;

import java.util.List;

@RestController
public class DossierMController {
    IDossierMService iDossierMService;

    public DossierMController(IDossierMService iDossierMService) {
        this.iDossierMService = iDossierMService;}

    @PostMapping("/dossierMedical")
    public DossierMedical ajouterDMedical(@RequestBody DossierMedical dossierMedical) {
        return iDossierMService.ajouterDMedical(dossierMedical);
    }
    @PutMapping("/dossierMedical")
    public DossierMedical modifierDMedical(@RequestBody DossierMedical dossierMedical) {
        return iDossierMService.modifierDMedical(dossierMedical);
    }
    @CrossOrigin
    @GetMapping("/dossierMedical")
    public List<DossierMedical> afficherDMedicaux() {
        return iDossierMService.afficherDMedicaux();
    }
    
    @GetMapping("/dossierMedicalArchivés")
    public List<DossierMedical> afficherDMedicauxArchivé() {
        return iDossierMService.afficherDMedicauxArchivé();
    }
    
    @GetMapping("/dossierMedical/{id}")
    public DossierMedical afficherDMedical(@PathVariable ("id") int id) {
        return iDossierMService.afficherDMedical(id);
    }
    @DeleteMapping("/dossierMedical/{id}")
    public void supprimerDMedical(@PathVariable ("id") int id) {
        iDossierMService.supprimerDMedical(id);
    }

    @GetMapping("/retrieveDossier")
    public List<DossierMedical> retrieveSubscriptionsByDates() {
        return iDossierMService.retrieveSubscriptionsByDates();
    }
}

