package com.openclassrooms.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpotLightDto {

    private Long id;
    private String name;
    private String city;
    private String country;
    private boolean isOfficial;
    private Long topoId;
    private Long userId;
    private CotationDto cotationMin;
    private CotationDto cotationMax;
}
