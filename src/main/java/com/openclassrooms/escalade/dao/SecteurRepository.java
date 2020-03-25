package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.entities.Secteur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecteurRepository extends CrudRepository<Secteur, Long> {

    List<Secteur> findAll();
}
