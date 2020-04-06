package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dao.*;
import com.openclassrooms.escalade.dto.SpotDto;
import com.openclassrooms.escalade.dto.TopoDto;
import com.openclassrooms.escalade.dto.TopoLightDto;
import com.openclassrooms.escalade.dto.TopoSaveDto;
import com.openclassrooms.escalade.entities.Cotation;
import com.openclassrooms.escalade.entities.Spot;
import com.openclassrooms.escalade.entities.Topo;
import com.openclassrooms.escalade.entities.User;
import com.openclassrooms.escalade.mapper.TopoMapper;
import com.openclassrooms.escalade.model.TopoSearch;
import com.openclassrooms.escalade.services.TopoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopoServiceImpl implements TopoService {

    public final TopoRepository topoRepository;
    private final TopoMapper topoMapper;
    private final UserRepository userRepository;
    private final CotationRepository cotationRepository;
    private final SpotRepository spotRepository;

    public TopoDto create(TopoSaveDto topoSaveDto){
        //User user = userRepository.findById(topoSaveDto.getUserId()).orElseThrow(EntityNotFoundException::new); //TODO : voir comment récupérer l'utilisateur
        User user = userRepository.findById(1L).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMin = cotationRepository.findById(topoSaveDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(topoSaveDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        Topo topo = Topo.builder()
                .cotationMin(cotationMin)
                .cotationMax(cotationMax)
                .country(topoSaveDto.getCountry())
                .description(topoSaveDto.getDescription())
                .name(topoSaveDto.getName())
                .region(topoSaveDto.getRegion())
                .topoCreator(user)
                .build();
        return topoMapper.toTopoDto(topoRepository.save(topo));
    }

    public Page<TopoLightDto> findAll(TopoSearch searchCriteria, Pageable page){
        return topoRepository.findAll(TopoPredicateBuilder.buildSearch(searchCriteria), page).map(topoMapper::toTopoLightDto);
    }

    public TopoDto findById(Long id){
        return topoMapper.toTopoDto(topoRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public TopoDto update(TopoDto topoDto) {
        Topo topo = topoRepository.findById(topoDto.getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMin = cotationRepository.findById(topoDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(topoDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        List<Spot> spots = new ArrayList<>();
        for (SpotDto spotDto : topoDto.getSpots()) {
            spots.add(this.spotRepository.findById(spotDto.getId()).orElseThrow(EntityNotFoundException::new));
        }
        topo.setSpots(spots);
        topo.setCotationMin(cotationMin);
        topo.setCotationMax(cotationMax);
        topo.setCountry(topoDto.getCountry());
        topo.setDescription(topoDto.getDescription());
        topo.setName(topoDto.getName());
        topo.setRegion(topoDto.getRegion());
        return topoMapper.toTopoDto(topoRepository.save(topo));
    }

    public void delete(Long id) {
        Topo topo = topoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        topoRepository.delete(topo);
    }
}
