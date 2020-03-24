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

    private CotationDto cotationMin;
    private CotationDto cotationMax;
    private String country;
    private String description;
    private String name;
    private String region;
    private Long userId;
}
