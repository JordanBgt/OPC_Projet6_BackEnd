package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dto.SecteurDto;
import com.openclassrooms.escalade.entities.Secteur;
import com.openclassrooms.escalade.mapper.SecteurMapper;
import com.openclassrooms.escalade.repositories.SecteurRepository;
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

    public List<SecteurDto> findAll() {
        return secteurMapper.toListSecteurDto(secteurRepository.findAll());
    }

    public SecteurDto findById(Long id) {
        return secteurMapper.toSecteurDto(secteurRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public SecteurDto create(Secteur secteur) {
        return secteurMapper.toSecteurDto(secteurRepository.save(secteur));
    }

    public SecteurDto update(Secteur secteur) {
        return secteurMapper.toSecteurDto(secteurRepository.save(secteur));
    }

    public void delete(Long id) {
        Secteur secteur = secteurRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        secteurRepository.delete(secteur);
    }
}
