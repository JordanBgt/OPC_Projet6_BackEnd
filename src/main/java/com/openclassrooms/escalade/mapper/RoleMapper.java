package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.RoleDto;
import com.openclassrooms.escalade.entities.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toRoleDto(Role role);
    List<RoleDto> toListRoleDto(List<Role> roles);
}
