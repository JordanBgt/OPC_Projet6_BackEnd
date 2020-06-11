package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.SpotDto;
import com.openclassrooms.escalade.dto.SpotLightDto;
import com.openclassrooms.escalade.model.SpotSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface SpotService {

    Page<SpotLightDto> findAll(SpotSearch spotSearch, Pageable page);
    SpotDto findById(Long id);
    SpotDto createOrUpdate(SpotDto spot);
    SpotDto addPhoto(Long topoId, MultipartFile photo);
    void delete(Long id);
}
