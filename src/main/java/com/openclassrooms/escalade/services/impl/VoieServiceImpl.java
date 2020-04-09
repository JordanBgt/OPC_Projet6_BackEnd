package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dao.VoiePredicateBuilder;
import com.openclassrooms.escalade.dto.VoieDto;
import com.openclassrooms.escalade.dto.VoieLightDto;
import com.openclassrooms.escalade.dto.VoieSaveDto;
import com.openclassrooms.escalade.entities.Cotation;
import com.openclassrooms.escalade.entities.Voie;
import com.openclassrooms.escalade.mapper.VoieMapper;
import com.openclassrooms.escalade.dao.CotationRepository;
import com.openclassrooms.escalade.dao.VoieRepository;
import com.openclassrooms.escalade.model.VoieSearch;
import com.openclassrooms.escalade.services.VoieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class VoieServiceImpl implements VoieService {

    private final VoieRepository voieRepository;
    private final VoieMapper voieMapper;
    private final CotationRepository cotationRepository;

    public Page<VoieLightDto> findAll(VoieSearch searchCriteria, Pageable page) {
        return voieRepository.findAll(VoiePredicateBuilder.buildSearch(searchCriteria), page).map(voieMapper::toVoieLightDto);
    }

    public VoieDto findById(Long id) {
        return voieMapper.toVoieDto(voieRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public VoieDto create(VoieSaveDto voieSaveDto) {
        Cotation cotationMin = cotationRepository.findById(voieSaveDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(voieSaveDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        Voie voie = Voie.builder()
                .cotationMin(cotationMin)
                .cotationMax(cotationMax)
                .name(voieSaveDto.getName())
                .build();
        return voieMapper.toVoieDto(voieRepository.save(voie));
    }

    public VoieDto update(VoieSaveDto voieSaveDto, Long id) {
        Voie voie = voieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMin = cotationRepository.findById(voieSaveDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(voieSaveDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        voie.setName(voieSaveDto.getName());
        voie.setCotationMin(cotationMin);
        voie.setCotationMax(cotationMax);
        return voieMapper.toVoieDto(voieRepository.save(voie));
    }

    public void delete(Long id) {
        Voie voie = voieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        voieRepository.delete(voie);
    }
}
