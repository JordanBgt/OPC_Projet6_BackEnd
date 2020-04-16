package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.UserDto;
import com.openclassrooms.escalade.dto.UserLightDto;
import com.openclassrooms.escalade.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TopoMapper.class, SpotMapper.class, RoleMapper.class})
public interface UserMapper {
    UserDto toUserDto(User user);

    UserLightDto toUserLightDto(User user);

    List<UserDto> toListUserDto(List<User> users);
}
