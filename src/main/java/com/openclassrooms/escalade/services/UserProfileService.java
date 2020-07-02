package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.UserProfileDto;

public interface UserProfileService {
    UserProfileDto findUserProfile(Long userId);
}
