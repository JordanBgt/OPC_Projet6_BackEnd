package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dao.SpotPredicateBuilder;
import com.openclassrooms.escalade.dto.SpotDto;
import com.openclassrooms.escalade.dto.SpotLightDto;
import com.openclassrooms.escalade.dto.SpotSaveDto;
import com.openclassrooms.escalade.entities.Cotation;
import com.openclassrooms.escalade.entities.Spot;
import com.openclassrooms.escalade.entities.User;
import com.openclassrooms.escalade.mapper.SpotMapper;
import com.openclassrooms.escalade.dao.CotationRepository;
import com.openclassrooms.escalade.dao.SpotRepository;
import com.openclassrooms.escalade.dao.UserRepository;
import com.openclassrooms.escalade.model.SpotSearch;
import com.openclassrooms.escalade.services.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotServiceImpl implements SpotService {

    private final SpotRepository spotRepository;
    private final SpotMapper spotMapper;
    private final UserRepository userRepository;
    private final CotationRepository cotationRepository;

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
                .city(spotSaveDto.getCity())
                .country(spotSaveDto.getCountry())
                .description(spotSaveDto.getDescription())
                .user(user)
                .cotationMin(cotationMin)
                .cotationMax(cotationMax)
                .build();
        return spotMapper.toSpotDto(spotRepository.save(spot));
    }

    public SpotDto update(SpotSaveDto spotSaveDto, Long id) {
        Spot spot = spotRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMin = cotationRepository.findById(spotSaveDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(spotSaveDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        spot.setCity(spotSaveDto.getCity());
        spot.setCountry(spotSaveDto.getCountry());
        spot.setDescription(spotSaveDto.getDescription());
        spot.setCotationMin(cotationMin);
        spot.setCotationMax(cotationMax);
        return spotMapper.toSpotDto(spotRepository.save(spot));
    }

    public void delete(Long id) {
        Spot spot = spotRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        spotRepository.delete(spot);
    }
}
