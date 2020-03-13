package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.SecteurDto;
import com.openclassrooms.escalade.entities.Secteur;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VoieMapper.class, SpotMapper.class})
public interface SecteurMapper {

    SecteurDto toSecteurDto(Secteur secteur);
    List<SecteurDto> toListSecteurDto(List<Secteur> secteurs);
}
