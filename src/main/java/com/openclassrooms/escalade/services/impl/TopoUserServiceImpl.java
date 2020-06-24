package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dao.TopoUserRepository;
import com.openclassrooms.escalade.dao.UserRepository;
import com.openclassrooms.escalade.dto.TopoUserDto;
import com.openclassrooms.escalade.entities.TopoUser;
import com.openclassrooms.escalade.entities.User;
import com.openclassrooms.escalade.mapper.TopoUserMapper;
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

    public TopoUserDto updateTopoUser(TopoUserDto topoUserDto) {
        TopoUser topoUser = topoUserRepository.findById(topoUserDto.getId()).orElseThrow(EntityNotFoundException::new);
        User tenant = userRepository.findById(topoUserDto.getTenant().getId())
                .orElseThrow(EntityNotFoundException::new);
        topoUser.setAvailable(topoUserDto.isAvailable());
        topoUser.setTenant(tenant);
        topoUser.setBookingState(topoUserDto.getBookingState());
        topoUser.setBookingDate(LocalDateTime.now());
        return topoUserMapper.toTopoUserDto(topoUserRepository.save(topoUser));
    }
}
