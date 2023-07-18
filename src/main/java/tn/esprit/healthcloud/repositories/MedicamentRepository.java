package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.healthcloud.controllers.SampleController;
import tn.esprit.healthcloud.entities.Medicament;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament,Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Medicament m SET m.stock = :stock WHERE m.Id = :id")
    void updateStock(@Param("id") int id, @Param("stock") long stock);
    List<Medicament> findByStockLessThan(long stock);
    @Query("SELECT COUNT(DISTINCT m.nom) FROM Medicament m")
    int countByNom();
}
