package com.openclassrooms.escalade.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LongueurSearch {

    private String name;
    private Long cotationMinId;
    private Long cotationMaxId;
}
