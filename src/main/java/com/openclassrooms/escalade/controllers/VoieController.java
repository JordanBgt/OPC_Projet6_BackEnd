package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.VoieDto;
import com.openclassrooms.escalade.dto.VoieLightDto;
import com.openclassrooms.escalade.dto.VoieSaveDto;
import com.openclassrooms.escalade.entities.Voie;
import com.openclassrooms.escalade.model.VoieSearch;
import com.openclassrooms.escalade.services.VoieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/voies")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class VoieController {

    private final VoieService voieService;

    @GetMapping
    @ResponseBody
    public Page<VoieLightDto> getAllVoies(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) Long cotationMin,
                                          @RequestParam(required = false) Long cotationMax,
                                          @RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "20") Integer size,
                                          @RequestParam(defaultValue = "name") String sortBy,
                                          @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                          @RequestParam(defaultValue = "false") boolean unpaged) {
        VoieSearch searchCriteria = new VoieSearch(name, cotationMin, cotationMax);
        Pageable pageable = unpaged ? Pageable.unpaged() : PageRequest.of(page, size, direction, sortBy);
        return voieService.findAll(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public VoieDto getVoie(@PathVariable Long id) {
        return voieService.findById(id);
    }

    @PostMapping
    @ResponseBody
    public VoieDto createVoie(@RequestBody VoieSaveDto voie) {
        return voieService.create(voie);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public VoieDto updateVoie(@RequestBody VoieSaveDto voie,
                              @PathVariable Long id) {
        return voieService.update(voie, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoie(@PathVariable Long id) {
        try {
            voieService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
