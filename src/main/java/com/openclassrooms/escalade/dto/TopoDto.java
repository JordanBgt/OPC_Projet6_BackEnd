package com.openclassrooms.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopoDto {

    private CotationDto cotationMin;
    private CotationDto cotationMax;
    private String country;
    private String description;
    private Long id;
    private String name;
    private PhotoDto photo;
    private Date publicationDate;
    private String region;
    private List<SpotDto> spots;
    private Long creatorId;
    private List<TopoUserDto> topoUsers = new ArrayList<>();

    public void addTopoUser(TopoUserDto topoUser) {
        this.topoUsers.add(topoUser);
    }
}
