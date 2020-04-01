package com.openclassrooms.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopoLightDto {

    private Long id;
    private String name;
    private String country;
    private String region;
    private CotationDto cotationMin;
    private CotationDto cotationMax;
    private boolean available;
    private PhotoDto photo;

}
