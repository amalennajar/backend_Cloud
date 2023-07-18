package tn.esprit.healthcloud.repositories;

import tn.esprit.healthcloud.entities.Donateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonateurRepository extends JpaRepository<Donateur, Integer> {
}
