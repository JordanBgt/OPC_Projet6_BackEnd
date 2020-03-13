package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.SpotDto;
import com.openclassrooms.escalade.dto.SpotSaveDto;
import com.openclassrooms.escalade.services.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SpotController {

    private final SpotService spotService;

    @Autowired
    public SpotController(SpotService spotService) {
        this.spotService = spotService;
    }

    @GetMapping("/spots")
    @ResponseBody
    public List<SpotDto> getAllSpots() {
        return spotService.findAll();
    }

    @GetMapping("/spot/{id}")
    @ResponseBody
    public SpotDto getSpot(@PathVariable Long id) {
        return spotService.findById(id);
    }

    @PostMapping("/spot")
    @ResponseBody
    public SpotDto createSpot(@RequestBody SpotSaveDto spot) {
        return spotService.create(spot);
    }

    @PutMapping("/spot/{id}")
    @ResponseBody
    public SpotDto updateSpot(@RequestBody SpotSaveDto spot,
                              @PathVariable Long id) {
        return spotService.update(spot, id);
    }

    @DeleteMapping("/spot/{id}")
    public ResponseEntity<Void> deleteSpot(@PathVariable Long id) {
        try {
            spotService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
