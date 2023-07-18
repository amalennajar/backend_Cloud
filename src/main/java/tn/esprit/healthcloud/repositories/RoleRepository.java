package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.healthcloud.entities.ERole;
import tn.esprit.healthcloud.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(ERole name);
}
