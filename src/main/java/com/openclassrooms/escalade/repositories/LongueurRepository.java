package com.openclassrooms.escalade.repositories;

import com.openclassrooms.escalade.entities.Longueur;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LongueurRepository extends CrudRepository<Longueur, Long> {

    List<Longueur> findAll();
}
