package com.openclassrooms.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopoDto {

    private boolean available;
    private String cotation;
    private String country;
    private String description;
    private String duration;
    private Long id;
    private String name;
    private PhotoDto photo;
    private Date publicationDate;
    private String region;
    private List<SpotDto> spots;
    private Long creatorId;
    private Long tenantId;
}