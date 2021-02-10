package org.example.trafficlight.rest.core.traffic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.trafficlight.db.entity.Traffic;
import org.example.trafficlight.rest.core.Observer;
import org.example.trafficlight.rest.core.Road;
import org.example.trafficlight.rest.service.TrafficService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrafficObserver implements Observer {
    private final TrafficService trafficService;

    @Override
    public void notifyObserver(Road road, String carName) {
         String color = road.getTrafficLight().getColor();
        if (color != TrafficColor.GREEN) {
            log.error("FUCKING CAR {} !!!", carName);
        }
        Traffic traffic = new Traffic()
                .setCarName(carName)
                .setColor(color)
                .setRoadName(road.getName())
                .setTime(Instant.now());
        trafficService.save(traffic);
    }
}
