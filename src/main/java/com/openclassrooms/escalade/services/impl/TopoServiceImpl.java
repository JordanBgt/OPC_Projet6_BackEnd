package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dto.TopoDto;
import com.openclassrooms.escalade.entities.Topo;
import com.openclassrooms.escalade.mapper.TopoMapper;
import com.openclassrooms.escalade.repositories.TopoRepository;
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

    public TopoDto create(Topo topo){
        return topoMapper.toTopoDto(topoRepository.save(topo));
    }

    public List<TopoDto> findAll(){
        return topoMapper.toListTopoDto(topoRepository.findAll());
    }

    public TopoDto findById(Long id){
        return topoMapper.toTopoDto(topoRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public TopoDto update(Topo topo) {
        return topoMapper.toTopoDto(topoRepository.save(topo));
    }

    public void delete(Long id) {
        Topo topo = topoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        topoRepository.delete(topo);
    }

}
