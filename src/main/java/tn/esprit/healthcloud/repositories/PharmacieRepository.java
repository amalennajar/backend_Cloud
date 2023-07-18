package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.healthcloud.entities.Pharmacie;
@Repository
public interface PharmacieRepository extends JpaRepository<Pharmacie,Integer> {
}
