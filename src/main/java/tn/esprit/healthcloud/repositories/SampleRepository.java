package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.healthcloud.entities.Sample;

@Repository
public interface SampleRepository extends JpaRepository<Sample,Integer> {

}
