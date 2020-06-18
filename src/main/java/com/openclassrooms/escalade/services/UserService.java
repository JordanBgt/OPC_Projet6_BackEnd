package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.UserDto;

public interface UserService {

    UserDto findById(Long userId);
}
