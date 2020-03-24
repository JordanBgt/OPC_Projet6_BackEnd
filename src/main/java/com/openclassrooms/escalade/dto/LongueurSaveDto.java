package com.openclassrooms.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LongueurSaveDto {

    private CotationDto cotationMin;
    private CotationDto cotationMax;
    private String name;
    private Long voieId;
}
