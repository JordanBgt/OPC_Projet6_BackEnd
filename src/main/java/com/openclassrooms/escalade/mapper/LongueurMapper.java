package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.LongueurDto;
import com.openclassrooms.escalade.dto.LongueurLightDto;
import com.openclassrooms.escalade.entities.Longueur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CotationMapper.class})
public interface LongueurMapper {

    LongueurDto toLongueurDto(Longueur longueur);

    LongueurLightDto toLongueurLightDto(Longueur longueur);

    List<LongueurDto> toListLongueurDto(List<Longueur> longueurs);
}
