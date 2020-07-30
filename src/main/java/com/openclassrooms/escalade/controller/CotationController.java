package com.openclassrooms.escalade.controller;

import com.openclassrooms.escalade.dto.CotationDto;
import com.openclassrooms.escalade.entity.Cotation;
import com.openclassrooms.escalade.service.CotationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CotationController {

    private final CotationService cotationService;

    /**
     * Method to get a list of all cotations
     * URL : /api/cotations
     *
     * @return a list of all cotations
     *
     * @see CotationService#findAll()
     */
    @GetMapping
    @ResponseBody
    public List<CotationDto> getAllCotations() {
        log.info("Start recovery of all cotations");
        return this.cotationService.findAll();
    }
}
