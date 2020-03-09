package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.SpotDto;
import com.openclassrooms.escalade.entities.Spot;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CommentMapper.class, PhotoMapper.class, SecteurMapper.class})
public interface SpotMapper {

    SpotDto toSpotDto(Spot spot);
    List<SpotDto> toSpotListDto(List<Spot> spot);
}
