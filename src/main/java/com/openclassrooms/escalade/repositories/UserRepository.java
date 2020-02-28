package com.openclassrooms.escalade.repositories;

import com.openclassrooms.escalade.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUtilisateurByUsername(String username);
}
