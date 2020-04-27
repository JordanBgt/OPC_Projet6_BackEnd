package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.SecteurDto;
import com.openclassrooms.escalade.dto.SecteurLightDto;
import com.openclassrooms.escalade.entities.Secteur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = VoieMapper.class)
public interface SecteurMapper {

    @Mapping(target = "userId", source = "user.id")
    SecteurDto toSecteurDto(Secteur secteur);

    SecteurLightDto toSecteurLightDto(Secteur secteur);

    List<SecteurDto> toListSecteurDto(List<Secteur> secteurs);
}
