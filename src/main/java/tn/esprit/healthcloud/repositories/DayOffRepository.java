package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.healthcloud.entities.DayOffStatus;
import tn.esprit.healthcloud.entities.DayOff;
import tn.esprit.healthcloud.entities.User;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DayOffRepository extends JpaRepository<DayOff, Integer> {
    DayOff findById(int id);
    List<DayOff> findByStatus(DayOffStatus status);

    List<DayOff> findByUser(User user);

    @Query("SELECT d FROM DayOff d WHERE d.startDate >= :startDate AND d.endDate <= :endDate")
    List<DayOff> findDayOffsBetweenDates(@Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);
}
