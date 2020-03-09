package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dto.SpotDto;
import com.openclassrooms.escalade.entities.Spot;
import com.openclassrooms.escalade.mapper.SpotMapper;
import com.openclassrooms.escalade.repositories.SpotRepository;
import com.openclassrooms.escalade.services.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotServiceImpl implements SpotService {

    private final SpotRepository spotRepository;
    private final SpotMapper spotMapper;

    public List<SpotDto> findAll () {
        return spotMapper.toSpotListDto(spotRepository.findAll());
    }

    public SpotDto findById(Long id) {
        return spotMapper.toSpotDto(spotRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public SpotDto create(Spot spot) {
        return spotMapper.toSpotDto(spotRepository.save(spot));
    }

    public SpotDto update(Spot spot) {
        return spotMapper.toSpotDto(spotRepository.save(spot));
    }

    public void delete(Long id) {
        Spot spot = spotRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        spotRepository.delete(spot);
    }
}
