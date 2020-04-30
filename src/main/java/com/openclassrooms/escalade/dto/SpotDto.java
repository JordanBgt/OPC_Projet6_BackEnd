package com.openclassrooms.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpotDto {

    private Long id;
    private String city;
    private String country;
    private String description;
    private boolean isOfficial;
    private List<TopoLightDto> topos;
    private List<PhotoDto> photos;
    private Long userId;
    private String name;
    private CotationDto cotationMin;
    private CotationDto cotationMax;
}
