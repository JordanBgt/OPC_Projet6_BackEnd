package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.LongueurDto;
import com.openclassrooms.escalade.entities.Longueur;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = VoieMapper.class)
public interface LongueurMapper {

    LongueurDto toLongueurDto(Longueur longueur);
    List<LongueurDto> toListLongueurDto(List<Longueur> longueurs);
    List<Longueur> toListLongueur(List<LongueurDto> longueursDto);
}
