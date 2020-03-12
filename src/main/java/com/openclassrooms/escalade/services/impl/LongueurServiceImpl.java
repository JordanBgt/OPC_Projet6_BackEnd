package com.openclassrooms.escalade.services.impl;

import antlr.RecognitionException;
import com.openclassrooms.escalade.dto.LongueurDto;
import com.openclassrooms.escalade.dto.LongueurSaveDto;
import com.openclassrooms.escalade.entities.Longueur;
import com.openclassrooms.escalade.entities.Voie;
import com.openclassrooms.escalade.mapper.LongueurMapper;
import com.openclassrooms.escalade.repositories.LongueurRepository;
import com.openclassrooms.escalade.repositories.VoieRepository;
import com.openclassrooms.escalade.services.LongueurService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LongueurServiceImpl implements LongueurService {

    private final LongueurRepository longueurRepository;
    private final LongueurMapper longueurMapper;
    private final VoieRepository voieRepository;

    public List<LongueurDto> findAll() {
        return longueurMapper.toListLongueurDto(longueurRepository.findAll());
    }

    public LongueurDto findById(Long id) {
        return longueurMapper.toLongueurDto(longueurRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public LongueurDto create(LongueurSaveDto longueurSaveDto) {
        Voie voie = voieRepository.findById(longueurSaveDto.getVoieId()).orElseThrow(EntityNotFoundException::new);
        Longueur longueur = Longueur.builder()
                .cotation(longueurSaveDto.getCotation())
                .name(longueurSaveDto.getName())
                .voie(voie)
                .build();
        return longueurMapper.toLongueurDto(longueurRepository.save(longueur));
    }

    public LongueurDto update(LongueurSaveDto longueurSaveDto, Long id) {
        Longueur longueur = longueurRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Voie voie = voieRepository.findById(longueurSaveDto.getVoieId()).orElseThrow(EntityNotFoundException::new);
        longueur.setCotation(longueurSaveDto.getCotation());
        longueur.setName(longueurSaveDto.getName());
        longueur.setVoie(voie);
        return longueurMapper.toLongueurDto(longueurRepository.save(longueur));
    }

    public void delete(Long id) {
        Longueur longueur = longueurRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        longueurRepository.delete(longueur);
    }
}
