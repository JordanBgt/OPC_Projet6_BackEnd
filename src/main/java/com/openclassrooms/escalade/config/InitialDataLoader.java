package com.openclassrooms.escalade.config;

import com.openclassrooms.escalade.entities.Role;
import com.openclassrooms.escalade.entities.User;
import com.openclassrooms.escalade.dao.RoleRepository;
import com.openclassrooms.escalade.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(alreadySetup)
            return;
        createRoleIfNotFound(AuthoritiesConstants.ADMIN);
        createRoleIfNotFound(AuthoritiesConstants.USER);

        Role adminRole = roleRepository.findByName(AuthoritiesConstants.ADMIN);
        Role userRole = roleRepository.findByName(AuthoritiesConstants.USER);
        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setEmail("test@test.com");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);
        User user2 = new User();
        user2.setUsername("user");
        user2.setPassword(passwordEncoder.encode("user"));
        user2.setEmail("user@test.com");
        user2.setRoles(Arrays.asList(userRole));
        userRepository.save(user2);
        alreadySetup = true;
    }

    @Transactional
    private void createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
    }
}
