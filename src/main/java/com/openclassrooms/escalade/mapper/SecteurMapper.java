package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.SecteurDto;
import com.openclassrooms.escalade.dto.SecteurLightDto;
import com.openclassrooms.escalade.entities.Secteur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VoieMapper.class, SpotMapper.class})
public interface SecteurMapper {

    @Mapping(source="spot.id", target="spotId")
    SecteurDto toSecteurDto(Secteur secteur);

    @Mapping(source="spot.id", target="spotId")
    SecteurLightDto toSecteurLightDto(Secteur secteur);

    List<SecteurDto> toListSecteurDto(List<Secteur> secteurs);
}
