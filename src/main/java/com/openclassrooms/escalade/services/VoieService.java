package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.entities.Voie;

import java.util.List;

public interface VoieService {

    List<Voie> findAll();
    Voie findById(Long id);
    Voie create(Voie voie);
    Voie update(Voie voie);
    void delete(Long id);
}
