package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.LongueurDto;
import com.openclassrooms.escalade.entities.Longueur;

import java.util.List;

public interface LongueurService {

    List<LongueurDto> findAll();
    LongueurDto findById(Long id);
    LongueurDto create(Longueur longueur);
    LongueurDto update(Longueur longueur);
    void delete(Long id);
}
