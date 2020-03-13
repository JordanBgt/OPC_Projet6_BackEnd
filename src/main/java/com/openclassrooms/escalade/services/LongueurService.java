package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.LongueurDto;
import com.openclassrooms.escalade.dto.LongueurSaveDto;

import java.util.List;

public interface LongueurService {

    List<LongueurDto> findAll();
    LongueurDto findById(Long id);
    LongueurDto create(LongueurSaveDto longueur);
    LongueurDto update(LongueurSaveDto longueur, Long id);
    void delete(Long id);
}
