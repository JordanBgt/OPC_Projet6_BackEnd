package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.entities.Secteur;

import java.util.List;

public interface SecteurService {

    List<Secteur> findAll();
    Secteur findById(Long id);
    Secteur create(Secteur secteur);
    Secteur update(Secteur secteur);
    void delete(Long id);
}
