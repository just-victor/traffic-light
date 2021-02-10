package org.example.trafficlight.rest.core.traffic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrafficScheduler {
    private final List<TrafficLight> trafficLight;

    @Scheduled(fixedRateString = "${app.traffic-color-delay}")
    public void changeTrafficLightColor() {
        for (TrafficLight light : trafficLight) {
            light.changeColor();
        }
    }
}
