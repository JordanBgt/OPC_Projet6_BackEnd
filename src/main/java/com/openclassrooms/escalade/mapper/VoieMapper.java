package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.VoieDto;
import com.openclassrooms.escalade.entities.Voie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LongueurMapper.class, CotationMapper.class})
public interface VoieMapper {

    @Mappings({
            @Mapping(source = "secteur.id", target = "secteurId"),
            @Mapping(source = "cotationMin.label", target = "cotationMin"),
            @Mapping(source = "cotationMax.label", target = "cotationMax")
    })
    VoieDto toVoieDto(Voie voie);
    List<VoieDto> toListVoieDto(List<Voie> voies);
}
