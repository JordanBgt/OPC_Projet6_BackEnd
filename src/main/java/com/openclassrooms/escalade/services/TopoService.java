package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.TopoDto;
import com.openclassrooms.escalade.dto.TopoSaveDto;
import com.openclassrooms.escalade.entities.Topo;

import java.util.List;

public interface TopoService {

    List<TopoDto> findAll();
    TopoDto findById(Long id);
    TopoDto create(TopoSaveDto topo);
    TopoDto update(TopoSaveDto topo, Long id);
    void delete(Long id);
}
