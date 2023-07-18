package tn.esprit.healthcloud.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.DossierMedical;
import tn.esprit.healthcloud.entities.Patient;
import tn.esprit.healthcloud.repositories.DossierMRepository;
import tn.esprit.healthcloud.repositories.PatientRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientService implements IPatientService{

    PatientRepository patientRepository;
    DossierMRepository dossierMRepository;


    @Override
    public Patient ajouterPatient(Patient patient) {

        System.out.println("patietn ffrom service"+patient);

        if(!patientRepository.findAll().stream().filter(p->!p.getIsArchive())
                .map(Patient::getChambre).collect(Collectors.toList()).contains(patient.getChambre())) {
            patient.setIsArchive(false);
            patient.setDateCreation(LocalDate.now());
            patientRepository.save(patient);
            return patient;
        }
        else {
            return null;
        }

    }
     
    
    @Override
    public Patient toArchive( int id) {
    	Patient archived = patientRepository.findById(id).get();
    	archived.setIsArchive(true);
    	archived.setDateArchivage(LocalDateTime.now());
        return patientRepository.save(archived);
    }
    

    @Override
    public Patient modifierPatient( Patient patient) {

        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> afficherPatients() {
    	return patientRepository.findAll().stream().filter(e->!e.getIsArchive())
        		.collect(Collectors.toList());
    }
    
    @Override
    public List<Patient> afficherPatientsArchivés() {
    	return patientRepository.findAll().stream().filter(e->e.getIsArchive())
        		.collect(Collectors.toList());
    }

    @Override
    public Patient afficherPatient( int id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerPatient(int id) {
            patientRepository.deleteById(id);

    }

    @Override
    public Patient assignerDossierToPatient(Integer idP, Integer idD) {
        Patient patient = patientRepository.findById(idP).orElse(null);
        DossierMedical dossierMedical = dossierMRepository.findById(idD).orElse(null);
        patient.setDossierMedical(dossierMedical);
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> findByNomPAndPrenomP(String nom, String prenom) {
        return patientRepository.findByNomPAndPrenomP(nom,prenom);    }
}


