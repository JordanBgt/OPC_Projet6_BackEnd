package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dao.UserRepository;
import com.openclassrooms.escalade.entities.User;
import com.openclassrooms.escalade.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("L'utilisateur ayant pour pseudo " + username + " n'a pas été trouvé"));

        return UserDetailsImpl.build(user);
    }
}
