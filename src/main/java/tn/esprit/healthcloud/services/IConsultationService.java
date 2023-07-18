package tn.esprit.healthcloud.services;
import tn.esprit.healthcloud.entities.Assurance;
import tn.esprit.healthcloud.entities.Consultation;
import tn.esprit.healthcloud.entities.Facture;

import java.util.Date;
import java.util.List;

public interface IConsultationService {

        Consultation createConsultation(Consultation consultation);
        Consultation getConsultationById(Long id);
        List<Consultation> getAllConsultations();

        Consultation updateConsultation(Long consultationId, Consultation consultation);


}




