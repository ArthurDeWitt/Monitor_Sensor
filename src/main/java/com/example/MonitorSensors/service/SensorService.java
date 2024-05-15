package com.example.MonitorSensors.service;

import com.example.MonitorSensors.repository.SensorRepository;
import com.example.MonitorSensors.entity.Sensor;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;

    @PreAuthorize("hasRole('Administrator') or hasRole('Viewer')")
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @PreAuthorize("hasRole('Administrator') or hasRole('Viewer')")
    public Sensor getSensor(Long id) {
        return sensorRepository.findById(id).orElseThrow();
    }

    @PreAuthorize("hasRole('Administrator')")
    public Sensor createSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @PreAuthorize("hasRole('Administrator')")
    public Sensor updateSensor(Long id, Sensor sensor) {
        Sensor existingSensor = getSensor(id);
        existingSensor.setName(sensor.getName());
        existingSensor.setModel(sensor.getModel());
        existingSensor.setSensorFrom(sensor.getSensorFrom());
        existingSensor.setSensorTo(sensor.getSensorTo());
        existingSensor.setType(sensor.getType());
        existingSensor.setUnit(sensor.getUnit());
        existingSensor.setLocation(sensor.getLocation());
        existingSensor.setDescription(sensor.getDescription());
        return sensorRepository.save(existingSensor);
    }

    @PreAuthorize("hasRole('Administrator')")
    public void deleteSensor(Long id) {
        sensorRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('Administrator') or hasRole('Viewer')")
    public List<Sensor> searchSensors(String name, String model) {
        return sensorRepository.findByNameContainingOrModelContaining(name, model);
    }
}
