package com.openclassrooms.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LongueurLightDto {

    private Long id;
    private String name;
    private CotationDto cotationMin;
    private CotationDto cotationMax;
}
