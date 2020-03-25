package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.TopoDto;
import com.openclassrooms.escalade.dto.TopoSaveDto;
import com.openclassrooms.escalade.services.TopoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/topos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TopoController {

    private final TopoService topoService;

    @GetMapping
    @ResponseBody
    public List<TopoDto> getAllTopos() {
        return topoService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public TopoDto getTopo(@PathVariable Long id) {
        return topoService.findById(id);
    }

    @PostMapping
    @ResponseBody
    public TopoDto createTopo(@RequestBody TopoSaveDto topo) {
        return topoService.create(topo);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public TopoDto updateTop(@RequestBody TopoSaveDto topo, Long id) {
        return topoService.update(topo, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopo(@PathVariable Long id) {
        try {
            topoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
