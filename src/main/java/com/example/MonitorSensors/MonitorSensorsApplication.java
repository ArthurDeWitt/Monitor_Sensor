package com.example.MonitorSensors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {
		UserDetailsServiceAutoConfiguration.class
})
public class MonitorSensorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorSensorsApplication.class, args);
	}

}
