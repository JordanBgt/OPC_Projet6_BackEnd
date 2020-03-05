package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.entities.Longueur;

import java.util.List;

public interface LongueurService {

    List<Longueur> findAll();
    Longueur findById(Long id);
    Longueur create(Longueur longueur);
    Longueur update(Longueur longueur);
    void delete(Long id);
}
