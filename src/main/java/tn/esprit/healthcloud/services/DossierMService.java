package tn.esprit.healthcloud.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.DossierMedical;
import tn.esprit.healthcloud.repositories.DossierMRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DossierMService implements IDossierMService {

    DossierMRepository dossierMRepository;


    @Override
    public DossierMedical ajouterDMedical(DossierMedical dossierMedical) {
    	dossierMedical.setDateCreation(LocalDate.now());
        return dossierMRepository.save(dossierMedical);
    }

    @Override
    public DossierMedical modifierDMedical(DossierMedical dossierMedical) {
        dossierMedical.setDerniereMaj(LocalDate.now());
        return dossierMRepository.save(dossierMedical);

    }

    @Override
    public List<DossierMedical> afficherDMedicaux() {
//        return dossierMRepository.findAll().stream().filter(e->!e.getIsArchive())
//        		.collect(Collectors.toList());
    	 return dossierMRepository.findAll().stream().filter(e->e.getPatient()!=null&& !e.getPatient().getIsArchive())
         		.collect(Collectors.toList());
    }
    
    @Override
    public List<DossierMedical> afficherDMedicauxArchivé() {
        return dossierMRepository.findAll().stream().filter(e->e.getPatient()!=null&& e.getPatient().getIsArchive())
        		.collect(Collectors.toList());
    }


    @Override
    public DossierMedical afficherDMedical(int id) {
        return dossierMRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerDMedical(int id) {
        dossierMRepository.deleteById(id);

    }

    @Override
    public List<DossierMedical> retrieveSubscriptionsByDates() {
        return dossierMRepository.retrieveSubscriptionsByDates();
    }
}

