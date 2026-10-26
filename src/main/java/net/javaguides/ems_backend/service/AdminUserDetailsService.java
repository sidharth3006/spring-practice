package net.javaguides.ems_backend.service;

import net.javaguides.ems_backend.entity.User;
import net.javaguides.ems_backend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AdminUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));


        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles()
                .build();
    }


}
