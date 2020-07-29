package com.openclassrooms.escalade.service;

import com.openclassrooms.escalade.dao.*;
import com.openclassrooms.escalade.dao.predicate.SecteurPredicateBuilder;
import com.openclassrooms.escalade.dto.*;
import com.openclassrooms.escalade.entity.Secteur;
import com.openclassrooms.escalade.entity.Spot;
import com.openclassrooms.escalade.entity.User;
import com.openclassrooms.escalade.mapper.SecteurMapper;
import com.openclassrooms.escalade.model.SecteurSearch;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Service to manage Secteur
 *
 * @see SecteurDto
 * @see SecteurLightDto
 * @see SecteurRepository
 * @see SecteurMapper
 */
@Service
@RequiredArgsConstructor
public class SecteurService {

    private final SecteurRepository secteurRepository;
    private final SecteurMapper secteurMapper;
    private final UserRepository userRepository;
    private final SpotRepository spotRepository;
    private final VoieService voieService;
    private final VoieRepository voieRepository;

    /**
     * Method to retrieve all the secteurs
     *
     * @param searchCriteria search criteria
     * @param page pagination criteria
     *
     * @return page of SecteurLightDto
     *
     * @see SecteurSearch
     * @see SecteurPredicateBuilder
     * @see SecteurRepository#findAll(Predicate, Pageable)
     */
    public Page<SecteurLightDto> findAll(SecteurSearch searchCriteria, Pageable page) {
        return secteurRepository.findAll(SecteurPredicateBuilder.buildSearch(searchCriteria), page).map(secteurMapper::toSecteurLightDto);
    }

    /**
     * Method to get a secteur by its id
     *
     * @param id id of the requested secteur
     *
     * @return SecteurDto
     *
     * @see SecteurRepository#findById(Object)
     * @see EntityNotFoundException
     */
    public SecteurDto findById(Long id) {
        return secteurMapper.toSecteurDto(secteurRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Method to create or upadate a Secteur
     *
     * @param secteurDto the secteur to save
     *
     * @return the secteur saved
     *
     * @see SecteurRepository#save(Object)
     * @see EntityNotFoundException
     */
    public SecteurDto createOrUpdate(SecteurDto secteurDto) {
        User user = userRepository.findById(secteurDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        Spot spot = spotRepository.findById(secteurDto.getSpotId()).orElseThrow(EntityNotFoundException::new);
        Secteur secteur = Secteur.builder()
                .id(secteurDto.getId())
                .user(user)
                .name(secteurDto.getName())
                .description(secteurDto.getDescription())
                .spot(spot)
                .build();
        return secteurMapper.toSecteurDto(secteurRepository.save(secteur));
    }

    /**
     * Method to delete a Secteur. We must first remove the voies linked to the Secteur to delete
     *
     * @param id id of the secteur to delete
     *
     * @see SecteurRepository#delete(Object)
     * @see EntityNotFoundException
     */
    public void delete(Long id) {
        List<Long> voiesId = voieRepository.findAllBySecteurId(id);
        if (voiesId != null) {
            for(Long voieId: voiesId) {
                voieService.delete(voieId);
            }
        }
        Secteur secteur = secteurRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        secteurRepository.delete(secteur);
    }
}
