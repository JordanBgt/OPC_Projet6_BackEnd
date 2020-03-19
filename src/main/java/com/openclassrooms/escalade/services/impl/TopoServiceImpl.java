package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dto.TopoDto;
import com.openclassrooms.escalade.dto.TopoSaveDto;
import com.openclassrooms.escalade.entities.Topo;
import com.openclassrooms.escalade.entities.User;
import com.openclassrooms.escalade.mapper.TopoMapper;
import com.openclassrooms.escalade.repositories.TopoRepository;
import com.openclassrooms.escalade.repositories.UserRepository;
import com.openclassrooms.escalade.services.TopoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopoServiceImpl implements TopoService {

    public final TopoRepository topoRepository;
    private final TopoMapper topoMapper;
    private final UserRepository userRepository;

    public TopoDto create(TopoSaveDto topoSaveDto){
        User user = userRepository.findById(topoSaveDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        Topo topo = Topo.builder()
                .cotation(topoSaveDto.getCotation())
                .country(topoSaveDto.getCountry())
                .description(topoSaveDto.getDescription())
                .name(topoSaveDto.getName())
                .region(topoSaveDto.getRegion())
                .topoCreator(user)
                .build();
        return topoMapper.toTopoDto(topoRepository.save(topo));
    }

    public List<TopoDto> findAll(){
        return topoMapper.toListTopoDto(topoRepository.findAll());
    }

    public TopoDto findById(Long id){
        return topoMapper.toTopoDto(topoRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public TopoDto update(TopoSaveDto topoSaveDto, Long id) {
        Topo topo = topoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        topo.setCotation(topoSaveDto.getCotation());
        topo.setCountry(topoSaveDto.getCountry());
        topo.setDescription(topoSaveDto.getDescription());
        topo.setName(topoSaveDto.getName());
        topo.setRegion(topoSaveDto.getRegion());
        return topoMapper.toTopoDto(topoRepository.save(topo));
    }

    public void delete(Long id) {
        Topo topo = topoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        topoRepository.delete(topo);
    }

}
