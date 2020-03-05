package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.entities.Spot;

import java.util.List;

public interface SpotService {

    List<Spot> findAll();
    Spot findById(Long id);
    Spot create(Spot spot);
    Spot update(Spot spot);
    void delete(Long id);
}
