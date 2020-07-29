package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dao.*;
import com.openclassrooms.escalade.dao.predicate.VoiePredicateBuilder;
import com.openclassrooms.escalade.dto.VoieDto;
import com.openclassrooms.escalade.dto.VoieLightDto;
import com.openclassrooms.escalade.entities.*;
import com.openclassrooms.escalade.mapper.VoieMapper;
import com.openclassrooms.escalade.model.VoieSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoieService {

    private final VoieRepository voieRepository;
    private final VoieMapper voieMapper;
    private final CotationRepository cotationRepository;
    private final UserRepository userRepository;
    private final SecteurRepository secteurRepository;
    private final LongueurRepository longueurRepository;
    private final LongueurService longueurService;

    public Page<VoieLightDto> findAll(VoieSearch searchCriteria, Pageable page) {
        return voieRepository.findAll(VoiePredicateBuilder.buildSearch(searchCriteria), page).map(voieMapper::toVoieLightDto);
    }

    public VoieDto findById(Long id) {
        return voieMapper.toVoieDto(voieRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public VoieDto createOrUpdate(VoieDto voieDto) {
        Cotation cotationMin = cotationRepository.findById(voieDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(voieDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(voieDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        Secteur secteur = secteurRepository.findById(voieDto.getSecteurId()).orElseThrow(EntityNotFoundException::new);
        Voie voie = Voie.builder()
                .cotationMin(cotationMin)
                .cotationMax(cotationMax)
                .name(voieDto.getName())
                .user(user)
                .description(voieDto.getDescription())
                .id(voieDto.getId())
                .secteur(secteur)
                .build();
        return voieMapper.toVoieDto(voieRepository.save(voie));
    }

    public void delete(Long id) {
        List<Long> longueursId = longueurRepository.findAllByVoieId(id);
        if (longueursId != null) {
            for (Long longueurId: longueursId) {
                longueurService.delete(longueurId);
            }
        }
        Voie voie = voieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        voieRepository.delete(voie);
    }
}
