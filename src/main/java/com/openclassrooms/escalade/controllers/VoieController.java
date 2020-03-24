package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.VoieDto;
import com.openclassrooms.escalade.dto.VoieSaveDto;
import com.openclassrooms.escalade.services.VoieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/voies")
@CrossOrigin(origins = "*")
public class VoieController {

    private final VoieService voieService;

    public VoieController(VoieService voieService) {
        this.voieService = voieService;
    }

    @GetMapping
    @ResponseBody
    public List<VoieDto> getAllVoies() {
        return voieService.findAll();
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
