package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.repositories.PhotoRepository;
import com.openclassrooms.escalade.services.PhotoService;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }
}
