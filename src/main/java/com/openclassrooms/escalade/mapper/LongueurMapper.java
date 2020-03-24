package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.LongueurDto;
import com.openclassrooms.escalade.entities.Longueur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VoieMapper.class, CotationMapper.class})
public interface LongueurMapper {

    @Mappings({
            @Mapping(source = "voie.id", target = "voieId"),
            @Mapping(source = "cotationMin.label", target = "cotationMin"),
            @Mapping(source = "cotationMax.label", target = "cotationMax")
    })
    LongueurDto toLongueurDto(Longueur longueur);
    List<LongueurDto> toListLongueurDto(List<Longueur> longueurs);
}
