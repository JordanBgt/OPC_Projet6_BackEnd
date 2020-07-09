package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dao.*;
import com.openclassrooms.escalade.dto.*;
import com.openclassrooms.escalade.entities.*;
import com.openclassrooms.escalade.mapper.SpotMapper;
import com.openclassrooms.escalade.model.SpotSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpotService {

    private final SpotRepository spotRepository;
    private final SpotMapper spotMapper;
    private final UserRepository userRepository;
    private final CotationRepository cotationRepository;
    private final SecteurRepository secteurRepository;
    private final SecteurService secteurService;
    private final FileStorageService fileStorageService;
    private final PhotoRepository photoRepository;

    public Page<SpotLightDto> findAll (SpotSearch searchCriteria, Pageable page) {
        Page<SpotLightDto> results = spotRepository.findAll(SpotPredicateBuilder.buildSearch(searchCriteria), page).map(spotMapper::toSpotLightDto);
        results.forEach(spot -> {
            if (spot.getPhotos() != null && spot.getPhotos().size() > 0) {
                spot.getPhotos().forEach(photoDto -> {
                    photoDto.convertFileToBase64String(fileStorageService.load(photoDto.getName()));
                });
            }
        });
        return results;
    }

    public SpotDto findById(Long id) {
        SpotDto spot = spotMapper.toSpotDto(spotRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        this.getPhotosToBase64(spot);
        return spot;
    }

    public SpotDto createOrUpdate(SpotDto spotDto) {
        User user = userRepository.findById(spotDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMin = cotationRepository.findById(spotDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(spotDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        List<Photo> photos = new ArrayList<>();
        if(spotDto.getPhotos() != null && spotDto.getPhotos().size() > 0) {
            spotDto.getPhotos().forEach(photoDto -> photos.add(photoRepository.findById(photoDto.getId())
                    .orElseThrow(EntityNotFoundException::new)));
        }
        Spot spot = Spot.builder()
                .id(spotDto.getId())
                .name(spotDto.getName())
                .city(spotDto.getCity())
                .country(spotDto.getCountry())
                .description(spotDto.getDescription())
                .user(user)
                .cotationMin(cotationMin)
                .cotationMax(cotationMax)
                .isOfficial(spotDto.isOfficial())
                .photos(photos)
                .build();
        SpotDto result = spotMapper.toSpotDto(spotRepository.save(spot));
        this.getPhotosToBase64(result);
        return result;
    }

    public SpotDto addPhoto(Long topoId, MultipartFile file) {
        Spot spot = spotRepository.findById(topoId).orElseThrow(EntityNotFoundException::new);
        Photo photo = Photo.builder()
            .name(file.getOriginalFilename())
            .extension(Objects.requireNonNull(file.getContentType()).substring(file.getContentType().indexOf('/')+1))
            .build();
        try {
            fileStorageService.save(file);
            spot.getPhotos().add(photo);
            SpotDto result = spotMapper.toSpotDto(spotRepository.save(spot));
            this.getPhotosToBase64(result);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(Long id) {
        List<Long> secteursId = secteurRepository.findAllBySpotId(id);
        if (secteursId != null) {
            for (Long secteurId: secteursId) {
                secteurService.delete(secteurId);
            }
        }
        Spot spot = spotRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        spotRepository.delete(spot);
    }

    private void getPhotosToBase64(SpotDto spot) {
        if (spot.getPhotos() != null && spot.getPhotos().size() > 0) {
            spot.getPhotos().forEach(element ->
                    element.convertFileToBase64String(fileStorageService.load(element.getName())));
        }
    }
}
