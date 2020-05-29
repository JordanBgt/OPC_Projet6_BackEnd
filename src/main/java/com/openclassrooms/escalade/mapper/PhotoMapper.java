package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.PhotoDto;
import com.openclassrooms.escalade.entities.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

    @Mappings({
            @Mapping(target = "file", defaultValue = "null")
    })
    PhotoDto toPhotoDto(Photo photo);
    List<PhotoDto> toListPhotoDto(List<Photo> photos);
}
