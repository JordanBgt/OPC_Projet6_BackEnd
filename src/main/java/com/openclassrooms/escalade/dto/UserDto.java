package com.openclassrooms.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private List<RoleDto> roles;
    private List<TopoDto> toposCreated;
    private List<TopoDto> toposRent;
    private List<SpotDto> spotsCreated;

}
