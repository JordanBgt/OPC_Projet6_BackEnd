package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dto.VoieDto;
import com.openclassrooms.escalade.entities.Secteur;
import com.openclassrooms.escalade.entities.Voie;
import com.openclassrooms.escalade.mapper.LongueurMapper;
import com.openclassrooms.escalade.mapper.VoieMapper;
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

    public List<VoieDto> findAll() {
        return voieMapper.toListVoieDto(voieRepository.findAll());
    }

    public VoieDto findById(Long id) {
        return voieMapper.toVoieDto(voieRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public VoieDto create(VoieDto voieDto) {
        Voie voie = Voie.builder()
                .cotation(voieDto.getCotation())
                .name(voieDto.getName())
                .build();
        return voieMapper.toVoieDto(voieRepository.save(voie));
    }

    public VoieDto update(VoieDto voieDto, Long id) {
        Voie voie = voieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Secteur secteur = secteurRepository.findById(voieDto.getSecteurId()).orElseThrow(EntityNotFoundException::new);
        voie.setName(voieDto.getName());
        voie.setCotation(voieDto.getCotation());
        voie.setLongueurs(longueurMapper.toListLongueur(voieDto.getLongueurs()));
        voie.setSecteur(secteur);
        return voieMapper.toVoieDto(voieRepository.save(voie));
    }

    public void delete(Long id) {
        Voie voie = voieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        voieRepository.delete(voie);
    }
}
