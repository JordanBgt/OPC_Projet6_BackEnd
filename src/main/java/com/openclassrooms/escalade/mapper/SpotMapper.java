package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.SpotDto;
import com.openclassrooms.escalade.dto.SpotLightDto;
import com.openclassrooms.escalade.entities.Spot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PhotoMapper.class, SecteurMapper.class, CotationMapper.class, TopoMapper.class})
public interface SpotMapper {

    @Mapping(source = "user.id", target="userId")
    SpotDto toSpotDto(Spot spot);


    @Mapping(source = "user.id", target="userId")
    SpotLightDto toSpotLightDto(Spot spot);

    List<SpotDto> toSpotListDto(List<Spot> spot);
}
