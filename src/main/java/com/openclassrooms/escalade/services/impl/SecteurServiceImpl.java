package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dao.*;
import com.openclassrooms.escalade.dto.*;
import com.openclassrooms.escalade.entities.Secteur;
import com.openclassrooms.escalade.entities.Spot;
import com.openclassrooms.escalade.entities.User;
import com.openclassrooms.escalade.entities.Voie;
import com.openclassrooms.escalade.mapper.SecteurMapper;
import com.openclassrooms.escalade.model.SecteurSearch;
import com.openclassrooms.escalade.services.SecteurService;
import com.openclassrooms.escalade.services.VoieService;
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
public class SecteurServiceImpl implements SecteurService {

    private final SecteurRepository secteurRepository;
    private final SecteurMapper secteurMapper;
    private final UserRepository userRepository;
    private final SpotRepository spotRepository;
    private final VoieService voieService;
    private final VoieRepository voieRepository;

    public Page<SecteurLightDto> findAll(SecteurSearch searchCriteria, Pageable page) {
        return secteurRepository.findAll(SecteurPredicateBuilder.buildSearch(searchCriteria), page).map(secteurMapper::toSecteurLightDto);
    }

    public SecteurDto findById(Long id) {
        return secteurMapper.toSecteurDto(secteurRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public SecteurDto createOrUpdate(SecteurDto secteurDto) {
        User user = userRepository.findById(secteurDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        Spot spot = spotRepository.findById(secteurDto.getSpotId()).orElseThrow(EntityNotFoundException::new);
        Secteur secteur = Secteur.builder()
                .id(secteurDto.getId())
                .user(user)
                .name(secteurDto.getName())
                .description(secteurDto.getDescription())
                .spot(spot)
                .build();
        return secteurMapper.toSecteurDto(secteurRepository.save(secteur));
    }

    public void delete(Long id) {
        List<Long> voiesId = voieRepository.findAllBySecteurId(id);
        if (voiesId != null) {
            for(Long voieId: voiesId) {
                voieService.delete(voieId);
            }
        }
        Secteur secteur = secteurRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        secteurRepository.delete(secteur);
    }
}
