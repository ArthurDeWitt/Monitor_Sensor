package com.example.MonitorSensors.mapper;

import com.example.MonitorSensors.dto.RegistrationUserDto;
import com.example.MonitorSensors.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User registrationUserDtoToUser(RegistrationUserDto registrationUserDto);
}
