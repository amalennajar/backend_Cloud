package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.healthcloud.entities.Patient;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("SELECT p FROM Patient p WHERE p.nomP = :nom AND p.prenomP = :prenom")
    List<Patient> findByNomPAndPrenomP(@Param("nom") String nom, @Param("prenom") String prenom);

}
