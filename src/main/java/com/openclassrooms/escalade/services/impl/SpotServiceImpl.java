package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dao.*;
import com.openclassrooms.escalade.dto.SecteurLightDto;
import com.openclassrooms.escalade.dto.SpotDto;
import com.openclassrooms.escalade.dto.SpotLightDto;
import com.openclassrooms.escalade.dto.SpotSaveDto;
import com.openclassrooms.escalade.entities.Cotation;
import com.openclassrooms.escalade.entities.Secteur;
import com.openclassrooms.escalade.entities.Spot;
import com.openclassrooms.escalade.entities.User;
import com.openclassrooms.escalade.mapper.SpotMapper;
import com.openclassrooms.escalade.model.SpotSearch;
import com.openclassrooms.escalade.services.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotServiceImpl implements SpotService {

    private final SpotRepository spotRepository;
    private final SpotMapper spotMapper;
    private final UserRepository userRepository;
    private final CotationRepository cotationRepository;
    private final SecteurRepository secteurRepository;

    public Page<SpotLightDto> findAll (SpotSearch searchCriteria, Pageable page) {
        return spotRepository.findAll(SpotPredicateBuilder.buildSearch(searchCriteria), page).map(spotMapper::toSpotLightDto);
    }

    public SpotDto findById(Long id) {
        return spotMapper.toSpotDto(spotRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public SpotDto create(SpotSaveDto spotSaveDto) {
        User user = userRepository.findById(spotSaveDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMin = cotationRepository.findById(spotSaveDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(spotSaveDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        Spot spot = Spot.builder()
                .name(spotSaveDto.getName())
                .city(spotSaveDto.getCity())
                .country(spotSaveDto.getCountry())
                .description(spotSaveDto.getDescription())
                .user(user)
                .cotationMin(cotationMin)
                .cotationMax(cotationMax)
                .build();
        return spotMapper.toSpotDto(spotRepository.save(spot));
    }

    public SpotDto update(SpotDto spotDto) {
        Spot spot = spotRepository.findById(spotDto.getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMin = cotationRepository.findById(spotDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(spotDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        List<Secteur> secteurs = new ArrayList<>();
        for(SecteurLightDto secteur: spotDto.getSecteurs()) {
            secteurs.add(this.secteurRepository.findById(secteur.getId()).orElseThrow(EntityNotFoundException::new));
        }
        spot.setName(spotDto.getName());
        spot.setCity(spotDto.getCity());
        spot.setCountry(spotDto.getCountry());
        spot.setDescription(spotDto.getDescription());
        spot.setCotationMin(cotationMin);
        spot.setCotationMax(cotationMax);
        spot.setSecteurs(secteurs);
        return spotMapper.toSpotDto(spotRepository.save(spot));
    }

    public void delete(Long id) {
        Spot spot = spotRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        spotRepository.delete(spot);
    }
}
