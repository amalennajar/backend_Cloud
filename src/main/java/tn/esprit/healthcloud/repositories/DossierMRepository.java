package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.healthcloud.entities.DossierMedical;

import java.util.List;

@Repository
public interface DossierMRepository extends JpaRepository<DossierMedical, Integer> {


    @Query(value = "SELECT * FROM dossier_medical ORDER BY date_creation", nativeQuery = true)
    List<DossierMedical> retrieveSubscriptionsByDates();


}
