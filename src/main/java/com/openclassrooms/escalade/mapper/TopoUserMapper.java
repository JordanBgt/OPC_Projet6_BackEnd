package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.TopoUserDto;
import com.openclassrooms.escalade.dto.TopoUserLightDto;
import com.openclassrooms.escalade.entity.TopoUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface TopoUserMapper {

    TopoUserDto toTopoUserDto(TopoUser topoUser);
    TopoUserLightDto toTopoUserLightDto(TopoUser topoUser);
}
