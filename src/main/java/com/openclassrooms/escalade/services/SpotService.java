package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.SpotDto;
import com.openclassrooms.escalade.dto.SpotSaveDto;

import java.util.List;

public interface SpotService {

    List<SpotDto> findAll();
    SpotDto findById(Long id);
    SpotDto create(SpotSaveDto spot);
    SpotDto update(SpotSaveDto spot, Long id);
    void delete(Long id);
}
