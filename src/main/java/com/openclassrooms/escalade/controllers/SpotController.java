package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.SpotDto;
import com.openclassrooms.escalade.dto.SpotLightDto;
import com.openclassrooms.escalade.dto.SpotSaveDto;
import com.openclassrooms.escalade.model.SpotSearch;
import com.openclassrooms.escalade.services.SpotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/spots")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SpotController {

    private final SpotService spotService;

    @GetMapping
    @ResponseBody
    public Page<SpotLightDto> getAllSpots(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) String country,
                                          @RequestParam(required = false) String city,
                                          @RequestParam(required = false) boolean isOfficial,
                                          @RequestParam(required = false) Long cotationMinId,
                                          @RequestParam(required = false) Long cotationMaxId,
                                          @RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "20") Integer size,
                                          @RequestParam(defaultValue = "name") String sortBy,
                                          @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                          @RequestParam(defaultValue = "false") boolean unpaged) {
        SpotSearch searchCriteria = new SpotSearch(name, country, city, isOfficial, cotationMinId, cotationMaxId);
        Pageable pageable = unpaged ? Pageable.unpaged() : PageRequest.of(page, size, direction, sortBy);
        return spotService.findAll(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public SpotDto getSpot(@PathVariable Long id) {
        return spotService.findById(id);
    }

    @PostMapping
    @ResponseBody
    public SpotDto createSpot(@RequestBody SpotSaveDto spot) {
        return spotService.create(spot);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public SpotDto updateSpot(@RequestBody SpotSaveDto spot,
                              @PathVariable Long id) {
        return spotService.update(spot, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpot(@PathVariable Long id) {
        try {
            spotService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
