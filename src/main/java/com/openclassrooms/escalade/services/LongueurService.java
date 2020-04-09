package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.LongueurDto;
import com.openclassrooms.escalade.dto.LongueurLightDto;
import com.openclassrooms.escalade.dto.LongueurSaveDto;
import com.openclassrooms.escalade.model.LongueurSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LongueurService {

    Page<LongueurLightDto> findAll(LongueurSearch searchCriteria, Pageable page);
    LongueurDto findById(Long id);
    LongueurDto create(LongueurSaveDto longueur);
    LongueurDto update(LongueurDto longueur);
    void delete(Long id);
}
