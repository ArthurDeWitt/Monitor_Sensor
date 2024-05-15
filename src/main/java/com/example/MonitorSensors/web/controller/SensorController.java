package com.example.MonitorSensors.web.controller;

import com.example.MonitorSensors.auth.UserPrincipal;
import com.example.MonitorSensors.entity.Sensor;
import com.example.MonitorSensors.service.SensorService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
@AllArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @GetMapping
    @Secured({"ROLE_VIEWER", "ROLE_EDITOR"})
    public List<Sensor> getAllSensors(@AuthenticationPrincipal UserPrincipal user) {
        return sensorService.getAllSensors();
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public Sensor getSensor(@PathVariable Long id) {
        return sensorService.getSensor(id);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public Sensor createSensor(@RequestBody Sensor sensor) {
        return sensorService.createSensor(sensor);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public Sensor updateSensor(@PathVariable Long id, @RequestBody Sensor sensor) {
        return sensorService.updateSensor(id, sensor);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteSensor(@PathVariable Long id) {
        sensorService.deleteSensor(id);
    }

    @GetMapping("/search")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<Sensor> searchSensors(@RequestParam String name, @RequestParam String model) {
        return sensorService.searchSensors(name, model);
    }
}
