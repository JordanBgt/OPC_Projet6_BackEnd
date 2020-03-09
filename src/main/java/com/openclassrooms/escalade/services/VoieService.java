package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.VoieDto;
import com.openclassrooms.escalade.entities.Voie;

import java.util.List;

public interface VoieService {

    List<VoieDto> findAll();
    VoieDto findById(Long id);
    VoieDto create(Voie voie);
    VoieDto update(Voie voie);
    void delete(Long id);
}
