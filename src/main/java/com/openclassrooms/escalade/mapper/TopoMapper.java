package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.TopoDto;
import com.openclassrooms.escalade.entities.Topo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SpotMapper.class, CotationMapper.class})
public interface TopoMapper {

    @Mappings({
            @Mapping(source = "topoCreator.id", target = "creatorId"),
            @Mapping(source = "topoTenant.id", target = "tenantId"),
            @Mapping(source = "cotationMin.label", target = "cotationMin"),
            @Mapping(source = "cotationMax.label", target = "cotationMax")
    })
    TopoDto toTopoDto(Topo topo);
    List<TopoDto> toListTopoDto(List<Topo> topos);
}
