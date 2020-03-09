package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.SecteurDto;
import com.openclassrooms.escalade.entities.Secteur;

import java.util.List;

public interface SecteurService {

    List<SecteurDto> findAll();
    SecteurDto findById(Long id);
    SecteurDto create(Secteur secteur);
    SecteurDto update(Secteur secteur);
    void delete(Long id);
}
