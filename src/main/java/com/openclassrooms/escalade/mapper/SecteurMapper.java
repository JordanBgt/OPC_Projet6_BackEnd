package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.SecteurDto;
import com.openclassrooms.escalade.dto.SecteurLightDto;
import com.openclassrooms.escalade.entities.Secteur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SecteurMapper {

    @Mappings({
            @Mapping(target = "userId", source = "user.id"),
            @Mapping(target = "spotId", source = "spot.id")
    })
    SecteurDto toSecteurDto(Secteur secteur);

    @Mappings({
            @Mapping(target = "spotId", source = "spot.id")
    })
    SecteurLightDto toSecteurLightDto(Secteur secteur);

    List<Secteur> toListSecteur(List<SecteurDto> secteurDtos);
}
