package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.CotationDto;
import com.openclassrooms.escalade.mapper.CotationMapper;
import com.openclassrooms.escalade.dao.CotationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CotationService {

    private final CotationRepository cotationRepository;
    private final CotationMapper cotationMapper;

    public List<CotationDto> findAll() {
        return cotationMapper.toListCotationDto(cotationRepository.findAll());
    }

    public CotationDto findById(Long id) {
        return cotationMapper.toCotationDto(cotationRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
