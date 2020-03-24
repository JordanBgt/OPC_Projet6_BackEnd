package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.VoieDto;
import com.openclassrooms.escalade.dto.VoieSaveDto;

import java.util.List;

public interface VoieService {

    List<VoieDto> findAll();
    VoieDto findById(Long id);
    VoieDto create(VoieSaveDto voie);
    VoieDto update(VoieSaveDto voie, Long id);
    void delete(Long id);
}
