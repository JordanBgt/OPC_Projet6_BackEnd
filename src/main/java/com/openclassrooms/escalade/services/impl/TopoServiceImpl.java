package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.entities.Topo;
import com.openclassrooms.escalade.repositories.TopoRepository;
import com.openclassrooms.escalade.services.TopoService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TopoServiceImpl implements TopoService {

    public final TopoRepository topoRepository;

    public TopoServiceImpl(TopoRepository topoRepository){
        this.topoRepository = topoRepository;
    }

    public Topo create(Topo topo){
        return topoRepository.save(topo);
    }

    public List<Topo> findAll(){
        return topoRepository.findAll();
    }

    public Topo findById(Long id){
        return topoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Le topo n'a pas été trouvé"));
    }

    public Topo update(Topo topo) {
        return topoRepository.save(topo);
    }

    public void delete(Long id) {
        Topo topo = this.findById(id);
        topoRepository.delete(topo);
    }

}
