package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dto.PhotoDto;
import com.openclassrooms.escalade.mapper.PhotoMapper;
import com.openclassrooms.escalade.dao.PhotoRepository;
import com.openclassrooms.escalade.services.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final PhotoMapper photoMapper;

    @Override
    public PhotoDto create(PhotoDto photoDto) {
        return null;
    }

    @Override
    public PhotoDto update(PhotoDto photoDto) {
        return null;
    }

    @Override
    public void delete(Long photoId) {

    }
}
