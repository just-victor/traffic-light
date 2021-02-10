package org.example.trafficlight;

import org.example.trafficlight.rest.core.Road;
import org.example.trafficlight.rest.core.traffic.TrafficLight;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static org.example.trafficlight.rest.core.traffic.TrafficColor.GREEN;
import static org.example.trafficlight.rest.core.traffic.TrafficColor.RED;

@Configuration
public class AppConfig {

    @Bean
    public List<TrafficLight> getTrafficLight() {
        return List.of(new TrafficLight(1, GREEN), new TrafficLight(2, RED));
    }

    @Bean
    public List<Road> getRoads(List<TrafficLight> trafficLights) {

        return List.of(new Road("First", trafficLights.get(0)),
                new Road("Second", trafficLights.get(1)));
    }
}
