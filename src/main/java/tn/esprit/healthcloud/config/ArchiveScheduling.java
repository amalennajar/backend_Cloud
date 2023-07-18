package tn.esprit.healthcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import tn.esprit.healthcloud.entities.Patient;
import tn.esprit.healthcloud.repositories.PatientRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


@Configuration
@EnableScheduling
public class ArchiveScheduling {
	
	
	@Autowired
	private PatientRepository patientRepo; 

	

	   // @Scheduled(cron = "0 0 0 * * ?") // checking every midnight
		@Scheduled(cron = "0 * * * * *")	
	    public void archive() {
	    	
	//get patients to archive
	List<Patient> patientsToArchive = patientRepo.findAll()
			.stream()
		    .filter(patient -> {
		    	if(!patient.getIsArchive()) {
		    		LocalDate archivingDate = patient.getDateCreation();
			        archivingDate.plusDays(patient.getSejour());
			        return archivingDate.equals(LocalDate.now());
		    	}else {
		    		return false;
		    	}
		    })
		    .collect(Collectors.toList());
	
	//archiving
	patientsToArchive.forEach(patient->{
		patient.setIsArchive(true);
		patient.setDateArchivage(LocalDateTime.now());
	});
	System.out.println("////////////archiving//////////////////");
	patientRepo.saveAll(patientsToArchive);
//	
	
	    }
	    
	     
	    
		@Scheduled(cron = "0 * * * * *")	 // checking every 1 minutes
	    public void deleteFromArchive() {
	    	

			
			List<Patient> patientsToDeleteFromArchive = patientRepo.findAll()
			        .stream()
			        .filter(patient -> patient.getIsArchive())
			        .filter(patient -> {
			        	if(patient.getIsArchive()) {
			        		LocalDateTime deleteFromArchiveDate = patient.getDateArchivage().plusMinutes(3);
				            LocalDateTime now = LocalDateTime.now();
				            return deleteFromArchiveDate.truncatedTo(ChronoUnit.MINUTES)
				                    .isEqual(now.truncatedTo(ChronoUnit.MINUTES));
			        	}else {
			        		return false;
			        	}
			            
			        })
			        .collect(Collectors.toList());

			List<Integer> patientIdsToDelete = patientsToDeleteFromArchive.stream()
				    .map(Patient::getIdP)
				    .collect(Collectors.toList());

				patientRepo.deleteAllById(patientIdsToDelete);

	    }
	    
	    
	    
	    
	    

	    }
