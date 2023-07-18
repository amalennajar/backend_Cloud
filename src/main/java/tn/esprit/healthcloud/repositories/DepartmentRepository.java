package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.healthcloud.entities.Department;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department findById(int id);
    List<Department> findByDepNameContainingOrDepDescContaining(String searchTerm1, String searchTerm2);

}
