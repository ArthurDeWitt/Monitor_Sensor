package com.example.MonitorSensors.auth;

import com.example.MonitorSensors.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPrincipal {
    private Long id;
    private String username;
    private Role role;
}
