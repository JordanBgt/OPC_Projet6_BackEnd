package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.SecteurDto;
import com.openclassrooms.escalade.dto.SecteurSaveDTto;

import java.util.List;

public interface SecteurService {

    List<SecteurDto> findAll();
    SecteurDto findById(Long id);
    SecteurDto create(SecteurSaveDTto secteur);
    SecteurDto update(SecteurSaveDTto secteur, Long id);
    void delete(Long id);
}
