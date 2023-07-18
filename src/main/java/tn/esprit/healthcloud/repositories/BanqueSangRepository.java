package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.healthcloud.entities.BanqueSang;
import tn.esprit.healthcloud.entities.TypeSanguin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface BanqueSangRepository extends JpaRepository<BanqueSang, Integer> {
    BanqueSang findByTypeSanguin(TypeSanguin typeSanguin);

    @Transactional
    @Modifying
    @Query("UPDATE BanqueSang b SET b.quantiteTotale = :quantiteTotale, b.sangRetire = :sangRetire WHERE b.idBanqueSang = :idBanqueSang")
    void updateQuantiteAndSangRetire(@Param("idBanqueSang") int idBanqueSang, @Param("quantiteTotale") int quantiteTotale, @Param("sangRetire") int sangRetire);
  

}
