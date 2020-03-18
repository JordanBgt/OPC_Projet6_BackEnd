package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dto.SecteurDto;
import com.openclassrooms.escalade.dto.SecteurSaveDTto;
import com.openclassrooms.escalade.entities.Secteur;
import com.openclassrooms.escalade.entities.Spot;
import com.openclassrooms.escalade.mapper.SecteurMapper;
import com.openclassrooms.escalade.repositories.SecteurRepository;
import com.openclassrooms.escalade.repositories.SpotRepository;
import com.openclassrooms.escalade.services.SecteurService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SecteurServiceImpl implements SecteurService {

    private final SecteurRepository secteurRepository;
    private final SecteurMapper secteurMapper;
    private final SpotRepository spotRepository;

    public List<SecteurDto> findAll() {
        return secteurMapper.toListSecteurDto(secteurRepository.findAll());
    }

    public SecteurDto findById(Long id) {
        return secteurMapper.toSecteurDto(secteurRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public SecteurDto create(SecteurSaveDTto secteurSaveDTto) {
        Spot spot = spotRepository.findById(secteurSaveDTto.getSpotId()).orElseThrow(EntityNotFoundException::new);
        Secteur secteur = Secteur.builder()
                .name(secteurSaveDTto.getName())
                .spot(spot)
                .build();
        return secteurMapper.toSecteurDto(secteurRepository.save(secteur));
    }

    public SecteurDto update(SecteurSaveDTto secteurSaveDTto, Long id) {
        Secteur secteur = secteurRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Spot spot = spotRepository.findById(secteurSaveDTto.getSpotId()).orElseThrow(EntityNotFoundException::new);
        secteur.setName(secteurSaveDTto.getName());
        secteur.setSpot(spot);
        return secteurMapper.toSecteurDto(secteurRepository.save(secteur));
    }

    public void delete(Long id) {
        Secteur secteur = secteurRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        secteurRepository.delete(secteur);
    }
}