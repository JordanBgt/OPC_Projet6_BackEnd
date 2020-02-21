package com.openclassrooms.escalade.repositories;

import com.openclassrooms.escalade.entities.Secteur;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SecteurRepository extends CrudRepository<Secteur, Long> {

    List<Secteur> findAll();
}
