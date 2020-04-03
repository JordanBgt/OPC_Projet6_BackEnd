package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.SpotDto;
import com.openclassrooms.escalade.dto.SpotLightDto;
import com.openclassrooms.escalade.entities.Spot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CommentMapper.class, PhotoMapper.class, SecteurMapper.class, CotationMapper.class})
public interface SpotMapper {

    @Mappings({
            @Mapping(source = "topo.id", target="topoId"),
            @Mapping(source = "user.id", target="userId")
    })
    SpotDto toSpotDto(Spot spot);

    @Mappings({
            @Mapping(source = "topo.id", target="topoId"),
            @Mapping(source = "user.id", target="userId")
    })
    SpotLightDto toSpotLightDto(Spot spot);

    List<SpotDto> toSpotListDto(List<Spot> spot);
}
