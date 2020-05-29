package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.PhotoDto;

public interface PhotoService {

    PhotoDto create(PhotoDto photoDto);
    PhotoDto update(PhotoDto photoDto);
    void delete(Long photoId);
}
