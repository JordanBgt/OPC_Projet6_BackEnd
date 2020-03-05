package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.VoieDto;
import com.openclassrooms.escalade.entities.Voie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoieMapper {

    @Mapping(source = "secteur.id", target = "secteurId")
    VoieDto toVoieDto(Voie voie);
    List<VoieDto> toListVoieDto(List<Voie> voies);
}
