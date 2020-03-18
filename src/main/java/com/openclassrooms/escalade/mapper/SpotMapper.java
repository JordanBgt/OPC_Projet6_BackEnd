package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.SpotDto;
import com.openclassrooms.escalade.entities.Spot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CommentMapper.class, PhotoMapper.class, SecteurMapper.class})
public interface SpotMapper {

    @Mapping(source="topo.id", target="topoId")
    SpotDto toSpotDto(Spot spot);
    List<SpotDto> toSpotListDto(List<Spot> spot);
}
