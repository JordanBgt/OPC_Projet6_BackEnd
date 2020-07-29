package com.openclassrooms.escalade.service;

import com.openclassrooms.escalade.dto.CotationDto;
import com.openclassrooms.escalade.mapper.CotationMapper;
import com.openclassrooms.escalade.dao.CotationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Service to manage Cotation
 *
 * @see CotationDto
 * @see CotationMapper
 * @see CotationRepository
 */
@Service
@RequiredArgsConstructor
public class CotationService {

    private final CotationRepository cotationRepository;
    private final CotationMapper cotationMapper;

    /**
     * Method to retrievel all the cotations
     *
     * @return a list of CotationDto
     *
     * @see CotationRepository#findAll()
     */
    public List<CotationDto> findAll() {
        return cotationMapper.toListCotationDto(cotationRepository.findAll());
    }

    /**
     * Method to get a cotation by its id
     *
     * @param id id of the requested cotation
     *
     * @return CotationDto
     *
     * @see CotationRepository#findById(Object)
     * @see EntityNotFoundException
     */
    public CotationDto findById(Long id) {
        return cotationMapper.toCotationDto(cotationRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
