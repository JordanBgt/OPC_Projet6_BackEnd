package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.TopoDto;
import com.openclassrooms.escalade.dto.TopoSaveDto;
import com.openclassrooms.escalade.services.TopoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TopoController {

    private final TopoService topoService;

    @Autowired
    public TopoController(TopoService topoService) {
        this.topoService = topoService;
    }

    @GetMapping("/topos")
    @ResponseBody
    public List<TopoDto> getAllTopos() {
        return topoService.findAll();
    }

    @GetMapping("/topo/{id}")
    @ResponseBody
    public TopoDto getTopo(@PathVariable Long id) {
        return topoService.findById(id);
    }

    @PostMapping("/topo")
    @ResponseBody
    public TopoDto createTopo(@RequestBody TopoSaveDto topo) {
        return topoService.create(topo);
    }

    @PutMapping("/topo/{id}")
    @ResponseBody
    public TopoDto updateTop(@RequestBody TopoSaveDto topo, Long id) {
        return topoService.update(topo, id);
    }

    @DeleteMapping("/topo/{id}")
    public ResponseEntity<Void> deleteTopo(@PathVariable Long id) {
        try {
            topoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
