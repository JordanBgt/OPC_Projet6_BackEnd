package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.entities.Topo;

import java.util.List;

public interface TopoService {

    List<Topo> findAll();
    Topo findById(Long id);
    Topo create(Topo topo);
    Topo update(Topo topo);
    void delete(Long id);
}
