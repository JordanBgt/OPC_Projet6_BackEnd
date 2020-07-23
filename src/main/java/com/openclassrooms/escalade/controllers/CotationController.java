package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.CotationDto;
import com.openclassrooms.escalade.entities.Cotation;
import com.openclassrooms.escalade.services.CotationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controler to handle cotation
 *
 * @see Cotation
 * @see CotationDto
 * @see CotationService
 */
@RestController
@RequestMapping("api/cotations")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CotationController {

    private final CotationService cotationService;

    /**
     * Method to get a list of all cotations
     * URL : localhost:8080/api/cotations
     *
     * @return a list of all cotations
     *
     * @see CotationService#findAll()
     */
    @GetMapping
    @ResponseBody
    public List<CotationDto> getAllCotations() {
        return this.cotationService.findAll();
    }

    /**
     * Method to get one cotation by its id
     *
     * @param id id of the cotation searched
     *
     * @return a cotation
     *
     * @see CotationService#findById(Long)
     */
    @GetMapping("/{id}")
    @ResponseBody
    public CotationDto getCotation(@PathVariable Long id) {
        return this.cotationService.findById(id);
    }
}
