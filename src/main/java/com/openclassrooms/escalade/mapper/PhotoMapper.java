package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.PhotoDto;
import com.openclassrooms.escalade.entities.Photo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

    PhotoDto toPhotoDto(Photo photo);
    List<PhotoDto> toListPhotoDto(List<Photo> photos);
}
