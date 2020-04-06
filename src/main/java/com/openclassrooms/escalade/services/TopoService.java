package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.TopoDto;
import com.openclassrooms.escalade.dto.TopoLightDto;
import com.openclassrooms.escalade.dto.TopoSaveDto;
import com.openclassrooms.escalade.model.TopoSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TopoService {

    Page<TopoLightDto> findAll(TopoSearch searchCriteria, Pageable page);
    TopoDto findById(Long id);
    TopoDto create(TopoSaveDto topo);
    TopoDto update(TopoDto topo);
    void delete(Long id);
}
