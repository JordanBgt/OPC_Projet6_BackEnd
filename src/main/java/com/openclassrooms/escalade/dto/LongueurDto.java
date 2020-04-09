package com.openclassrooms.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LongueurDto {

    private CotationDto cotationMin;
    private CotationDto cotationMax;
    private Long id;
    private String name;
    private String description;
}
