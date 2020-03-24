package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dto.VoieDto;
import com.openclassrooms.escalade.dto.VoieSaveDto;
import com.openclassrooms.escalade.entities.Cotation;
import com.openclassrooms.escalade.entities.Secteur;
import com.openclassrooms.escalade.entities.Voie;
import com.openclassrooms.escalade.mapper.LongueurMapper;
import com.openclassrooms.escalade.mapper.VoieMapper;
import com.openclassrooms.escalade.repositories.CotationRepository;
import com.openclassrooms.escalade.repositories.SecteurRepository;
import com.openclassrooms.escalade.repositories.VoieRepository;
import com.openclassrooms.escalade.services.VoieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoieServiceImpl implements VoieService {

    private final VoieRepository voieRepository;
    private final VoieMapper voieMapper;
    private final LongueurMapper longueurMapper;
    private final SecteurRepository secteurRepository;
    private final CotationRepository cotationRepository;

    public List<VoieDto> findAll() {
        return voieMapper.toListVoieDto(voieRepository.findAll());
    }

    public VoieDto findById(Long id) {
        return voieMapper.toVoieDto(voieRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public VoieDto create(VoieSaveDto voieSaveDto) {
        Cotation cotationMin = cotationRepository.findById(voieSaveDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(voieSaveDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        Secteur secteur = secteurRepository.findById(voieSaveDto.getSecteurId()).orElseThrow(EntityNotFoundException::new);
        Voie voie = Voie.builder()
                .cotationMin(cotationMin)
                .cotationMax(cotationMax)
                .name(voieSaveDto.getName())
                .secteur(secteur)
                .build();
        return voieMapper.toVoieDto(voieRepository.save(voie));
    }

    public VoieDto update(VoieSaveDto voieSaveDto, Long id) {
        Voie voie = voieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Secteur secteur = secteurRepository.findById(voieSaveDto.getSecteurId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMin = cotationRepository.findById(voieSaveDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(voieSaveDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        voie.setName(voieSaveDto.getName());
        voie.setCotationMin(cotationMin);
        voie.setCotationMax(cotationMax);
        voie.setSecteur(secteur);
        return voieMapper.toVoieDto(voieRepository.save(voie));
    }

    public void delete(Long id) {
        Voie voie = voieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        voieRepository.delete(voie);
    }
}
