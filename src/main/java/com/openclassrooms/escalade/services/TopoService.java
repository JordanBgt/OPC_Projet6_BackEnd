package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.entities.Topo;
import com.openclassrooms.escalade.repositories.TopoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopoService {

    public final TopoRepository topoRepository;

    public TopoService(TopoRepository topoRepository){
        this.topoRepository = topoRepository;
    }

    public Topo save(Topo topo){
        return topoRepository.save(topo);
    }

    public List<Topo> findAll(){
        return topoRepository.findAll();
    }

    public Optional<Topo> findById(Long id){
        return topoRepository.findById(id);
    }

}
