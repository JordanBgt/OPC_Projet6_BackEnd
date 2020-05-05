package com.openclassrooms.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SecteurDto {

    private String name;
    private Long id;
    private String description;
    private Long userId;
    private Long spotId;
}
