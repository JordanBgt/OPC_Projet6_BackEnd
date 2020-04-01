package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.TopoDto;
import com.openclassrooms.escalade.dto.TopoLightDto;
import com.openclassrooms.escalade.dto.TopoSaveDto;
import com.openclassrooms.escalade.model.TopoSearch;
import com.openclassrooms.escalade.services.TopoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/topos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class TopoController {

    private final TopoService topoService;

    @GetMapping
    @ResponseBody
    public Page<TopoLightDto> getAllTopos(@RequestParam(required = false) String country,
                                          @RequestParam(required = false) String name,
                                          @RequestParam(required = false) boolean isAvailable,
                                          @RequestParam(required = false) Long cotationMin,
                                          @RequestParam(required = false) Long cotationMax,
                                          @RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "20") Integer size,
                                          @RequestParam(defaultValue = "name") String sortBy,
                                          @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                          @RequestParam(defaultValue = "false") boolean unpaged) {
        TopoSearch searchCriteria = new TopoSearch(country, name, isAvailable, cotationMin, cotationMax);
        Pageable pageable = unpaged ? Pageable.unpaged() : PageRequest.of(page, size, direction, sortBy);
        return topoService.findAll(searchCriteria, pageable);
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
