package com.openclassrooms.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpotSaveDto {

    private Long id;
    private String city;
    private String country;
    private String description;
    private Long userId;
    private String name;
    private CotationDto cotationMin;
    private CotationDto cotationMax;
    private boolean isOfficial;
}
