package com.example.MonitorSensors.web.controller;

import com.example.MonitorSensors.auth.TokenProvider;
import com.example.MonitorSensors.dto.LoginUserDto;
import com.example.MonitorSensors.dto.RegistrationUserDto;
import com.example.MonitorSensors.entity.User;
import com.example.MonitorSensors.mapper.UserMapper;
import com.example.MonitorSensors.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final TokenProvider tokenProvider;

    @PostMapping("/registration")
    public User registration(@RequestBody RegistrationUserDto dto) {
        return userService.add(userMapper.registrationUserDtoToUser(dto));
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginUserDto dto) {
        User user = userService.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        return tokenProvider.generateToken(user);
    }
}
