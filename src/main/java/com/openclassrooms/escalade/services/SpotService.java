package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.SpotDto;
import com.openclassrooms.escalade.dto.SpotLightDto;
import com.openclassrooms.escalade.dto.SpotSaveDto;
import com.openclassrooms.escalade.model.SpotSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SpotService {

    Page<SpotLightDto> findAll(SpotSearch spotSearch, Pageable page);
    SpotDto findById(Long id);
    SpotDto create(SpotSaveDto spot);
    SpotDto update(SpotSaveDto spot, Long id);
    void delete(Long id);
}
