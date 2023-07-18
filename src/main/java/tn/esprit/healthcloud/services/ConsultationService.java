package tn.esprit.healthcloud.services;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.Consultation;
import tn.esprit.healthcloud.repositories.ConsultationRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;



@Service
public class ConsultationService implements IConsultationService {

    private final ConsultationRepository consultationRepository;


    public ConsultationService(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;

    }


    @Override
    public Consultation createConsultation(Consultation consultation) {
        Consultation savedConsultation = consultationRepository.save(consultation);
        return savedConsultation;
    }

    @Override
    public Consultation getConsultationById(Long id) {
        Consultation consultation = consultationRepository.findById(id).get();
        return consultation;
    }

    @Override
    public List<Consultation> getAllConsultations() {
        List<Consultation> consultations = consultationRepository.findAll();
        return consultations;
    }


    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id parameter cannot be null");
        }
        consultationRepository.deleteById(id);
    }

    public Consultation updateConsultation(Long id, Consultation consultation) {
        Optional<Consultation> existingConsultation = consultationRepository.findById(id);

        if (existingConsultation.isPresent()) {
            Consultation updatedConsultation = existingConsultation.get();
            updatedConsultation.setDate(consultation.getDate());
            updatedConsultation.setTime(consultation.getTime());
            updatedConsultation.setStatusRDV(consultation.getStatusRDV());
            updatedConsultation.setSuivi(consultation.getSuivi());

            return consultationRepository.save(updatedConsultation);
        } else {
            throw new NoSuchElementException("Consultation with id " + id + " not found");
        }
    }
}


