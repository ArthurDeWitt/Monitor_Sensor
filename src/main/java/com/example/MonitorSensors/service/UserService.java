package com.example.MonitorSensors.service;

import com.example.MonitorSensors.entity.User;
import com.example.MonitorSensors.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsernameAndPassword(String username, String password) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            User user = byUsername.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            } else {
                throw new UsernameNotFoundException("Username not found");
            }
        }
        throw new UsernameNotFoundException("Username not found");
    }
}
