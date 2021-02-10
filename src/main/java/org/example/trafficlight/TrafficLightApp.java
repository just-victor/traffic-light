package org.example.trafficlight;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@Slf4j
@EnableScheduling
@SpringBootApplication
@EnableJpaRepositories
@RequiredArgsConstructor
public class TrafficLightApp {
    private final Crossroads crossroads;

    public static void main(String[] args) {
        SpringApplication.run(TrafficLightApp.class, args);
    }

    @PostConstruct
    public void start() {
        crossroads.startTraffic();
    }
}
