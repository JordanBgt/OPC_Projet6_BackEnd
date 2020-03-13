package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.VoieDto;

import java.util.List;

public interface VoieService {

    List<VoieDto> findAll();
    VoieDto findById(Long id);
    VoieDto create(VoieDto voie);
    VoieDto update(VoieDto voie, Long id);
    void delete(Long id);
}
