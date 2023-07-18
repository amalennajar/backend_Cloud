package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.healthcloud.entities.FireBaseToken;

@Repository
public interface FBTRepository extends JpaRepository<FireBaseToken, Integer> {
    FireBaseToken findByUserId(long userId);
}
