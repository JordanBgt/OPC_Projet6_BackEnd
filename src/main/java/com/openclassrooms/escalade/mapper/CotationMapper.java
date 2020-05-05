package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.CotationDto;
import com.openclassrooms.escalade.entities.Cotation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CotationMapper {
    CotationDto toCotationDto(Cotation cotation);
    List<CotationDto> toListCotationDto(List<Cotation> cotations);

    Cotation toCotation(CotationDto cotationDto);
}
