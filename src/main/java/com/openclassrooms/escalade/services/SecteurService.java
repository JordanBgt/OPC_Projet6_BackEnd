package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.SecteurDto;
import com.openclassrooms.escalade.dto.SecteurLightDto;
import com.openclassrooms.escalade.model.SecteurSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SecteurService {

    Page<SecteurLightDto> findAll(SecteurSearch searchCriteria, Pageable page);
    SecteurDto findById(Long id);
    SecteurDto createOrUpdate(SecteurDto secteur);
    void delete(Long id);
}
