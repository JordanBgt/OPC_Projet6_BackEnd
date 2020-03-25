package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dto.CotationDto;
import com.openclassrooms.escalade.mapper.CotationMapper;
import com.openclassrooms.escalade.dao.CotationRepository;
import com.openclassrooms.escalade.services.CotationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CotationServiceImpl implements CotationService {

    private final CotationRepository cotationRepository;
    private final CotationMapper cotationMapper;

    @Override
    public List<CotationDto> findAll() {
        return cotationMapper.toListCotationDto(cotationRepository.findAll());
    }

    @Override
    public CotationDto findById(Long id) {
        return cotationMapper.toCotationDto(cotationRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
