package com.openclassrooms.escalade.model;

import com.openclassrooms.escalade.entities.Cotation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopoSearch {

    private String country;
    private String name;
    private boolean available;
    private Cotation cotationMin;
    private Cotation cotationMax;
}
