package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dto.VoieDto;
import com.openclassrooms.escalade.entities.Voie;
import com.openclassrooms.escalade.mapper.VoieMapper;
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

    public List<VoieDto> findAll() {
        return voieMapper.toListVoieDto(voieRepository.findAll());
    }

    public VoieDto findById(Long id) {
        return voieMapper.toVoieDto(voieRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public VoieDto create(Voie voie) {
        return voieMapper.toVoieDto(voieRepository.save(voie));
    }

    public VoieDto update(Voie voie) {
        return voieMapper.toVoieDto(voieRepository.save(voie));
    }

    public void delete(Long id) {
        Voie voie = voieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        voieRepository.delete(voie);
    }
}
