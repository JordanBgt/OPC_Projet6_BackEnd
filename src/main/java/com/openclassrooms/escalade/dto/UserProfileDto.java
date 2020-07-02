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
public class UserProfileDto {

    private UserDto user;
    private List<TopoLightDto> toposCreated;
    private List<TopoUserDto> toposOwned;
    private List<TopoUserLightDto> toposRent;
    private List<SpotLightDto> spotsCreated;
}
