package com.openclassrooms.escalade.repositories;

import com.openclassrooms.escalade.entities.Commentaire;
import org.springframework.data.repository.CrudRepository;

public interface CommentaireRepository extends CrudRepository<Commentaire, Integer> {
}
