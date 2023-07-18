package tn.esprit.healthcloud.repositories;

import tn.esprit.healthcloud.entities.Logistique;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.healthcloud.entities.Operation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Integer> {

    List<Operation> findByTypeOp(String typeOp);
    List<Operation> findByTypeOpContainingIgnoreCase(String typeOp);
    @Query("select o.logistiques from Operation o where o.idOp = :idOp")
    List<Logistique> findLogistiquesByOperation(@Param("idOp") int idOp);
    @Query("SELECT o.typeOp, COUNT(o) FROM Operation o GROUP BY o.typeOp")
    List<Object[]> countOperationsByTypeOp();
    @Query("SELECT DISTINCT o.typeOp FROM Operation o")
    List<String> findAllTypeOp();
    @Query("SELECT DISTINCT typeOp FROM Operation")
    List<String> findDistinctTypeOp();
    List<Operation> findBySuccess(boolean success);



}

