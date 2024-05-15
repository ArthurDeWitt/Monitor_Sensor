package com.example.MonitorSensors.repository;

import com.example.MonitorSensors.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findByNameContainingOrModelContaining(String name, String model);
}
