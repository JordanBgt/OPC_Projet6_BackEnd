package com.openclassrooms.escalade.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpotSearch {

    private String name;
    private String country;
    private String city;
    private boolean isOfficial;
    private Long cotationMinId;
    private Long cotationMaxId;
}
