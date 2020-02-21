package com.openclassrooms.escalade.repositories;

import com.openclassrooms.escalade.entities.Utilisateur;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {

    Optional<Utilisateur> findUtilisateurByUsername(String username);
}
