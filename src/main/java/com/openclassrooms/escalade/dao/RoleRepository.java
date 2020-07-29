package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.entities.Role;
import com.openclassrooms.escalade.model.ERole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository to handle Role DAO operations
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
