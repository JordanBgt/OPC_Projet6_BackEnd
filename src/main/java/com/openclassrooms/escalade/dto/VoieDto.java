package com.openclassrooms.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoieDto {

    private String cotationMin;
    private String cotationMax;
    private Long id;
    private List<LongueurDto> longueurs;
    private String name;
    private Long secteurId;
}
