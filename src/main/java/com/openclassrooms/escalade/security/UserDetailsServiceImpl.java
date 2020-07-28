package com.openclassrooms.escalade.security;

import com.openclassrooms.escalade.dao.UserRepository;
import com.openclassrooms.escalade.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Implements UserDetailsService to fin a User by his username
 *
 * @see UserDetailsService
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /**
     * Load a User by username
     *
     * @param username user's username
     * @return UserDetails object
     * @throws UsernameNotFoundException exception thrown if there is no match in the database for the given username
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("L'utilisateur ayant pour pseudo " + username + " n'a pas été trouvé"));

        return UserDetailsImpl.build(user);
    }
}
