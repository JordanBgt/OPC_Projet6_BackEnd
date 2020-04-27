package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dao.*;
import com.openclassrooms.escalade.dto.LongueurDto;
import com.openclassrooms.escalade.dto.VoieDto;
import com.openclassrooms.escalade.dto.VoieLightDto;
import com.openclassrooms.escalade.dto.VoieSaveDto;
import com.openclassrooms.escalade.entities.Cotation;
import com.openclassrooms.escalade.entities.Longueur;
import com.openclassrooms.escalade.entities.User;
import com.openclassrooms.escalade.entities.Voie;
import com.openclassrooms.escalade.mapper.VoieMapper;
import com.openclassrooms.escalade.model.VoieSearch;
import com.openclassrooms.escalade.services.VoieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoieServiceImpl implements VoieService {

    private final VoieRepository voieRepository;
    private final VoieMapper voieMapper;
    private final CotationRepository cotationRepository;
    private final LongueurRepository longueurRepository;
    private final UserRepository userRepository;

    public Page<VoieLightDto> findAll(VoieSearch searchCriteria, Pageable page) {
        return voieRepository.findAll(VoiePredicateBuilder.buildSearch(searchCriteria), page).map(voieMapper::toVoieLightDto);
    }

    public VoieDto findById(Long id) {
        return voieMapper.toVoieDto(voieRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public VoieDto create(VoieSaveDto voieSaveDto) {
        Cotation cotationMin = cotationRepository.findById(voieSaveDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(voieSaveDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(voieSaveDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        Voie voie = Voie.builder()
                .cotationMin(cotationMin)
                .cotationMax(cotationMax)
                .name(voieSaveDto.getName())
                .build();
        return voieMapper.toVoieDto(voieRepository.save(voie));
    }

    public VoieDto update(VoieDto voieDto) {
        Voie voie = voieRepository.findById(voieDto.getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMin = cotationRepository.findById(voieDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(voieDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        List<Longueur> longueurs = new ArrayList<>();
        for (LongueurDto longueur : voieDto.getLongueurs()) {
            longueurs.add(longueurRepository.findById(longueur.getId()).orElseThrow(EntityNotFoundException::new));
        }
        voie.setName(voieDto.getName());
        voie.setCotationMin(cotationMin);
        voie.setCotationMax(cotationMax);
        voie.setDescription(voieDto.getDescription());
        voie.setLongueurs(longueurs);
        return voieMapper.toVoieDto(voieRepository.save(voie));
    }

    public void delete(Long id) {
        Voie voie = voieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        voieRepository.delete(voie);
    }
}
