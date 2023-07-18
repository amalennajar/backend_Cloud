package tn.esprit.healthcloud.repositories;

import tn.esprit.healthcloud.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.healthcloud.entities.Logistique;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogistiqueRepository extends JpaRepository<Logistique, Integer> {

    List<Logistique> findByOperations(Operation operation);


    List<Logistique> findByTypeLogiContainingIgnoreCase(String typeLogi);

}

