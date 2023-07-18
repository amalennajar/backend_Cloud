package tn.esprit.healthcloud.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.healthcloud.entities.Image;
import tn.esprit.healthcloud.entities.Patient;
import tn.esprit.healthcloud.repositories.ImageRepository;
import tn.esprit.healthcloud.services.IDossierMService;
import tn.esprit.healthcloud.services.IImageService;
import tn.esprit.healthcloud.services.IPatientService;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.List;

@RestController
public class PatientController {

	@Autowired
    IPatientService iPatientService;
	@Autowired
    IDossierMService iDossierMedical;
	@Autowired
    private IImageService imageService;
	@Autowired
    private ImageRepository imageRepo;   


    public PatientController(IPatientService iPatientService) {
        this.iPatientService = iPatientService;
    }


    @PostMapping(value="/patient/{dateNaissance}/{idDossier}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @CrossOrigin
    public  ResponseEntity<String> ajouterPatient( @PathVariable("idDossier") int idDossier,
                                                   @PathVariable("dateNaissance") String dateNaissance,
                                                   @RequestPart("patient") String patient,
                                                   @RequestParam(name = "patientfile") MultipartFile patientFile) {

        //converting patient object from string to json
        Patient patientJson = new Patient();
        try {
            ObjectMapper mapper = new ObjectMapper();
            patientJson=mapper.readValue(patient, Patient.class);
        } catch (Exception e) {
            System.out.println(e.toString());
        }



        Image patientFiletosend = new Image();

        try {

            String fileName = imageService.save(patientFile);
            String imageUrl = imageService.getImageUrl(fileName);
            patientFiletosend.setPath(imageUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
        patientJson.setImage(patientFiletosend);




        patientJson.setDossierMedical(iDossierMedical.afficherDMedical(idDossier));
        patientJson.setDateNaissanceP(LocalDate.parse(dateNaissance));

        if(iPatientService.ajouterPatient(patientJson)!=null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Félicitation, patient crée avec succès");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Désolé, mais la chambre est déja Occupée ! ");

        }


    }


    @PostMapping(value="/patientnewDossier/{dateNaissance}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @CrossOrigin
    public  ResponseEntity<String> ajouterPatientt(

            @RequestPart("patient") String patient,
            @Nullable @RequestParam(name = "patientfile") MultipartFile patientFile,
            @PathVariable("dateNaissance") String dateNaissance
    ) {

        //converting patient object from string to json
        Patient patientJson = new Patient();
        try {

            ObjectMapper mapper = new ObjectMapper();
            patientJson=mapper.readValue(patient, Patient.class);
        } catch (Exception e) {
            System.out.println(e.toString());
        }



        Image patientFiletosend = new Image();

        try {

            String fileName = imageService.save(patientFile);
            String imageUrl = imageService.getImageUrl(fileName);

            System.out.println("testtest");
            System.out.println(imageUrl);
            patientFiletosend.setPath(imageUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
        patientJson.setImage(patientFiletosend);
        patientJson.setDateNaissanceP(LocalDate.parse(dateNaissance));
        patientJson.getDossierMedical().setDateCreation(LocalDate.now());

        //if room doesn't exists, patient will be stored to DB
        if(iPatientService.ajouterPatient(patientJson)!=null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Félicitation, patient crée avec succès");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Désolé, mais la chambre est déja Occupée ! ");

        }


    }



//    @PutMapping("/patienttoarchive/{id}")
//    public Patient patientToArchive(@PathVariable("id") int id) {
//        return iPatientService.toArchive(id);
//    }



    @PutMapping(value="/patient/{dateNaissance}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @CrossOrigin
    public  ResponseEntity<Patient> modifierPatient(

            @RequestPart("patient") String patient,
            @RequestParam(name = "patientfile") MultipartFile patientFile,
            @PathVariable("dateNaissance") String dateNaissance
    )  {

        //converting patient object from string to json
        Patient patientJson = new Patient();
        try {

            ObjectMapper mapper = new ObjectMapper();
            patientJson=mapper.readValue(patient, Patient.class);
        } catch (Exception e) {
            System.out.println(e.toString());
        }




        Image patientFiletosend = new Image();

        try {

            String fileName = imageService.save(patientFile);
            String imageUrl = imageService.getImageUrl(fileName);
            patientFiletosend.setPath(imageUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
        patientJson.setImage(patientFiletosend);




        patientJson.setDateNaissanceP(LocalDate.parse(dateNaissance));



        iPatientService.modifierPatient(patientJson);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(patientJson);
    }
    @GetMapping("/patient")
    @CrossOrigin
    public List<Patient> afficherPatients() {
        return iPatientService.afficherPatients();
    }
    
    
    @GetMapping("/patientArchived")
    @CrossOrigin
    public List<Patient> afficherPatientsArchived() {
        return iPatientService.afficherPatientsArchivés();
    }
    
    @GetMapping("/patient/{id}")
    public Patient afficherPatient(@PathVariable("id") int id) {
        return iPatientService.afficherPatient(id);
    }
    @DeleteMapping("/patient/{id}")
    @CrossOrigin
    public void supprimerPatient(@PathVariable("id")  int id) {
    	System.out.println("deletiiiiiiiiiiiiiing");
        iPatientService.supprimerPatient(id);
    }

    @PutMapping("/assign/{idP}/{idD}")
    public Patient assignerDossierToPatient(@PathVariable("idP")Integer idP, @PathVariable("idD")Integer idD) {
        return iPatientService.assignerDossierToPatient(idP, idD);
    }
}
