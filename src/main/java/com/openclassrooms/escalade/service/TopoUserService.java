package com.openclassrooms.escalade.service;

import com.openclassrooms.escalade.dao.TopoRepository;
import com.openclassrooms.escalade.dao.TopoUserRepository;
import com.openclassrooms.escalade.dao.UserRepository;
import com.openclassrooms.escalade.dto.TopoUserDto;
import com.openclassrooms.escalade.entity.TopoUser;
import com.openclassrooms.escalade.mapper.TopoUserMapper;
import com.openclassrooms.escalade.model.EBookingState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

/**
 * Service to manage TopoUSer
 *
 * @see TopoUserDto
 * @see TopoUserRepository
 * @see TopoUserMapper
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TopoUserService {

    private final TopoUserRepository topoUserRepository;
    private final UserRepository userRepository;
    private final TopoUserMapper topoUserMapper;
    private final TopoRepository topoRepository;

    /**
     * Method to update a topoUser
     *
     * @param topoUserDto topoUser updated to save
     *
     * @return TopoUserDto updated
     *
     * @see TopoUserRepository#save(Object)
     * @see EntityNotFoundException
     */
    public TopoUserDto updateTopoUser(TopoUserDto topoUserDto) {

        TopoUser topoUser = topoUserRepository.findById(topoUserDto.getId()).orElseThrow(EntityNotFoundException::new);

        // if we have a reservation made but the status has not been update (due to a problem with TS enum)
        if (topoUserDto.getBookingState() == null && topoUserDto.getTenant() != null) {
            topoUserDto.setBookingState(EBookingState.PENDING);
        }

        // If no BookingState is provided, it means that the topo is not booked, it is therefore available by default
        if (topoUserDto.getBookingState() == null) {
            topoUser.setAvailable(topoUserDto.isAvailable());
            // Else, if the BookingState is ACCEPTED or PENDING : the topoUser is unavailable. In other cases,  it is available
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
                default: // if BookingState REFUSED, CANCELED, FINISHED
                    topoUser.setTenant(null);
                    topoUser.setAvailable(true);
                    topoUser.setBookingState(null);
                    topoUser.setBookingDate(null);
                    break;
            }

        }
        return topoUserMapper.toTopoUserDto(topoUserRepository.save(topoUser));
    }

    /**
     * Method to create a TopoUser : when a user indicates that he owns the topo and wants to make it available for rental
     *
     * @param topoUserDto TopoUser to save
     *
     * @return TopoUserDto saved
     *
     * @see TopoUserRepository#save(Object)
     * @see EntityNotFoundException
     */
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
