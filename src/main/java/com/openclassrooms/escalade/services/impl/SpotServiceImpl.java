package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dao.*;
import com.openclassrooms.escalade.dto.*;
import com.openclassrooms.escalade.entities.Cotation;
import com.openclassrooms.escalade.entities.Secteur;
import com.openclassrooms.escalade.entities.Spot;
import com.openclassrooms.escalade.entities.User;
import com.openclassrooms.escalade.mapper.SpotMapper;
import com.openclassrooms.escalade.model.SpotSearch;
import com.openclassrooms.escalade.services.SecteurService;
import com.openclassrooms.escalade.services.SpotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpotServiceImpl implements SpotService {

    private final SpotRepository spotRepository;
    private final SpotMapper spotMapper;
    private final UserRepository userRepository;
    private final CotationRepository cotationRepository;
    private final SecteurRepository secteurRepository;
    private final SecteurService secteurService;

    public Page<SpotLightDto> findAll (SpotSearch searchCriteria, Pageable page) {
        return spotRepository.findAll(SpotPredicateBuilder.buildSearch(searchCriteria), page).map(spotMapper::toSpotLightDto);
    }

    public SpotDto findById(Long id) {
        return spotMapper.toSpotDto(spotRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public SpotDto createOrUpdate(SpotDto spotDto) {
        User user = userRepository.findById(spotDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMin = cotationRepository.findById(spotDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(spotDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        Spot spot = Spot.builder()
                .name(spotDto.getName())
                .city(spotDto.getCity())
                .country(spotDto.getCountry())
                .description(spotDto.getDescription())
                .user(user)
                .cotationMin(cotationMin)
                .cotationMax(cotationMax)
                .isOfficial(spotDto.isOfficial())
                .build();
        return spotMapper.toSpotDto(spotRepository.save(spot));
    }

    public void delete(Long id) {
        List<Long> secteursId = secteurRepository.findAllBySpotId(id);
        if (secteursId != null) {
            for (Long secteurId: secteursId) {
                secteurService.delete(secteurId);
            }
        }
        Spot spot = spotRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        spotRepository.delete(spot);
    }
}
