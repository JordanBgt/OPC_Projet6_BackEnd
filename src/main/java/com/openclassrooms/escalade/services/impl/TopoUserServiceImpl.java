package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dao.TopoRepository;
import com.openclassrooms.escalade.dao.TopoUserRepository;
import com.openclassrooms.escalade.dao.UserRepository;
import com.openclassrooms.escalade.dto.TopoUserDto;
import com.openclassrooms.escalade.entities.TopoUser;
import com.openclassrooms.escalade.mapper.TopoUserMapper;
import com.openclassrooms.escalade.model.EBookingState;
import com.openclassrooms.escalade.services.TopoUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class TopoUserServiceImpl implements TopoUserService {

    private final TopoUserRepository topoUserRepository;
    private final UserRepository userRepository;
    private final TopoUserMapper topoUserMapper;
    private final TopoRepository topoRepository;

    public TopoUserDto updateTopoUser(TopoUserDto topoUserDto) { //TODO : réfléchir à un autre algo

        TopoUser topoUser = topoUserRepository.findById(topoUserDto.getId()).orElseThrow(EntityNotFoundException::new);

        // Si on a une réservation de faite mais que le statut n'a pas été mis à jour -> problème énum TS
        if (topoUserDto.getBookingState() == null && topoUserDto.getTenant() != null) {
            topoUserDto.setBookingState(EBookingState.PENDING);
        }

        if (topoUserDto.getBookingState() == null) {
            topoUser.setAvailable(topoUserDto.isAvailable());
        } else {
            switch (topoUserDto.getBookingState()) {
                case ACCEPTED:
                case PENDING:
                    topoUser.setTenant(userRepository.findById(topoUserDto.getTenant().getId())
                            .orElseThrow(EntityNotFoundException::new));
                    topoUser.setAvailable(false);
                    topoUser.setBookingState(topoUserDto.getBookingState());
                    topoUser.setBookingDate(LocalDateTime.now());
                    break;
                case REFUSED:
                case CANCELED:
                case FINISHED:
                    topoUser.setTenant(null);
                    topoUser.setAvailable(true);
                    topoUser.setBookingState(null);
                    topoUser.setBookingDate(null);
                    break;
            }

        }
        return topoUserMapper.toTopoUserDto(topoUserRepository.save(topoUser));
    }

    public TopoUserDto createTopoUser(TopoUserDto topoUserDto) {
        TopoUser topoUser = new TopoUser();
        topoUser.setOwner(userRepository.findById(topoUserDto.getOwner().getId())
                .orElseThrow(EntityNotFoundException::new));
        topoUser.setTopo(topoRepository.findById(topoUserDto.getTopo().getId())
        .orElseThrow(EntityNotFoundException::new));
        topoUser.setAvailable(true);
        return topoUserMapper.toTopoUserDto(topoUserRepository.save(topoUser));
    }
}
