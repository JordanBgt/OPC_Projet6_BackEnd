package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.VoieDto;
import com.openclassrooms.escalade.dto.VoieLightDto;
import com.openclassrooms.escalade.entities.Voie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CotationMapper.class})
public interface VoieMapper {

    @Mappings({
            @Mapping(target = "userId", source = "user.id"),
            @Mapping(target = "secteurId", source = "secteur.id")
    })
    VoieDto toVoieDto(Voie voie);

    VoieLightDto toVoieLightDto(Voie voie);

    List<VoieDto> toListVoieDto(List<Voie> voies);
}
