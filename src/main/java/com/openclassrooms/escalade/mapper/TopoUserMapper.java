package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.TopoUserDto;
import com.openclassrooms.escalade.entities.TopoUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface TopoUserMapper {

    TopoUserDto toTopoUserDto(TopoUser topoUser);
}
