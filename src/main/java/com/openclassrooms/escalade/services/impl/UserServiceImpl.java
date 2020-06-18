package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dao.UserRepository;
import com.openclassrooms.escalade.dto.UserDto;
import com.openclassrooms.escalade.mapper.UserMapper;
import com.openclassrooms.escalade.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto findById(Long userId) {
        return userMapper.toUserDto(this.userRepository.findById(userId).orElseThrow(EntityNotFoundException::new));
    }
}
