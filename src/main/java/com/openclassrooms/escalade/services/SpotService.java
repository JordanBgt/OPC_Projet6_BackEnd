package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.SpotDto;
import com.openclassrooms.escalade.entities.Spot;

import java.util.List;

public interface SpotService {

    List<SpotDto> findAll();
    SpotDto findById(Long id);
    SpotDto create(Spot spot);
    SpotDto update(Spot spot);
    void delete(Long id);
}
