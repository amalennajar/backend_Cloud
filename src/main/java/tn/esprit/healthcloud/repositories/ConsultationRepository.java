package tn.esprit.healthcloud.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.healthcloud.entities.Consultation;
import tn.esprit.healthcloud.entities.StatusRDV;

import java.util.Date;
import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
        /*List<Consultation> findByPatientNom(String nom);
        List<Consultation> findByMedecinNom(String nom);*/
        List<Consultation> findByDateBetween(Date startDate, Date endDate);
        List<Consultation> findByStatusRDV(StatusRDV status);
    }



