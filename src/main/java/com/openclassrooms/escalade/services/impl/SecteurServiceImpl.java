package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dao.SecteurPredicateBuilder;
import com.openclassrooms.escalade.dao.VoieRepository;
import com.openclassrooms.escalade.dto.*;
import com.openclassrooms.escalade.entities.Secteur;
import com.openclassrooms.escalade.entities.Voie;
import com.openclassrooms.escalade.mapper.SecteurMapper;
import com.openclassrooms.escalade.dao.SecteurRepository;
import com.openclassrooms.escalade.dao.SpotRepository;
import com.openclassrooms.escalade.model.SecteurSearch;
import com.openclassrooms.escalade.services.SecteurService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SecteurServiceImpl implements SecteurService {

    private final SecteurRepository secteurRepository;
    private final SecteurMapper secteurMapper;
    private final VoieRepository voieRepository;

    public Page<SecteurLightDto> findAll(SecteurSearch searchCriteria, Pageable page) {
        return secteurRepository.findAll(SecteurPredicateBuilder.buildSearch(searchCriteria), page).map(secteurMapper::toSecteurLightDto);
    }

    public SecteurDto findById(Long id) {
        return secteurMapper.toSecteurDto(secteurRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public SecteurDto create(SecteurSaveDTto secteurSaveDTto) {
        Secteur secteur = Secteur.builder()
                .name(secteurSaveDTto.getName())
                .description(secteurSaveDTto.getDescription())
                .build();
        return secteurMapper.toSecteurDto(secteurRepository.save(secteur));
    }

    public SecteurDto update(SecteurDto secteurDto) {
        Secteur secteur = secteurRepository.findById(secteurDto.getId()).orElseThrow(EntityNotFoundException::new);
        List<Voie> voies = new ArrayList<>();
        for (VoieDto voie : secteurDto.getVoies()) {
            voies.add(this.voieRepository.findById(voie.getId()).orElseThrow(EntityNotFoundException::new));
        }
        secteur.setName(secteurDto.getName());
        secteur.setDescription(secteurDto.getDescription());
        secteur.setVoies(voies);
        return secteurMapper.toSecteurDto(secteurRepository.save(secteur));
    }

    public void delete(Long id) {
        Secteur secteur = secteurRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        secteurRepository.delete(secteur);
    }
}
