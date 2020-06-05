package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.TopoDto;
import com.openclassrooms.escalade.dto.TopoLightDto;
import com.openclassrooms.escalade.model.TopoSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface TopoService {

    Page<TopoLightDto> findAll(TopoSearch searchCriteria, Pageable page);
    TopoDto findById(Long id);
    TopoDto createOrUpdate(TopoDto topo);
    TopoDto addPhoto(Long topoId, MultipartFile photo);
    void delete(Long id);
}
