package com.openclassrooms.escalade.repositories;

import com.openclassrooms.escalade.entities.Utilisateur;
import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
}
