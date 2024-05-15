package com.example.MonitorSensors.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
@Table(name = "sensors")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String name;

    @Column(nullable = false, length = 15)
    @NotBlank(message = "Model cannot be blank")
    @Size(max = 15, message = "Model must not exceed 15 characters")
    private String model;

    @Column(nullable = false)
    private Integer sensorFrom;

    @Column(nullable = false)
    private Integer sensorTo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SensorType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SensorUnit unit;

    @Column(length = 40)
    @Size(max = 40, message = "Location must not exceed 40 characters")
    private String location;

    @Column(length = 200)
    @Size(max = 200, message = "Description must not exceed 200 characters")
    private String description;
}
