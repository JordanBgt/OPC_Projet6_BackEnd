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

    private Long idSpot;
    private String city;
    private String country;
    private String description;
    private boolean isOfficial;
    private Long topoId;
    private List<CommentDto> comments;
    private List<PhotoDto> photos;
    private List<SecteurDto> secteurs;
    private Long userId;
    private String name;
    private String cotation;
}
