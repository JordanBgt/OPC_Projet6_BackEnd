package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.LongueurDto;
import com.openclassrooms.escalade.entities.Longueur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = VoieMapper.class)
public interface LongueurMapper {

    @Mapping(source="voie.id", target="voieId")
    LongueurDto toLongueurDto(Longueur longueur);
    List<LongueurDto> toListLongueurDto(List<Longueur> longueurs);
    List<Longueur> toListLongueur(List<LongueurDto> longueursDto);
}
