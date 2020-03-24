package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.CotationDto;
import com.openclassrooms.escalade.entities.Cotation;

import java.util.List;

public interface CotationService {
    List<CotationDto> findAll();
    CotationDto findById(Long id);
}
