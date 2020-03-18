package com.openclassrooms.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopoSaveDto {

    private String cotation;
    private String country;
    private String description;
    private String duration;
    private String name;
    private String region;
    private Long userId;
}