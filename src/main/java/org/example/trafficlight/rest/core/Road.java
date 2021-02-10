package org.example.trafficlight.rest.core;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.trafficlight.ColorLightObserver;
import org.example.trafficlight.rest.core.traffic.TrafficLight;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.example.trafficlight.rest.core.traffic.TrafficColor.GREEN;

@Slf4j
@Data
public class Road implements ColorLightObserver {
    private final String name;
    private final TrafficLight trafficLight;
    private final Queue<Car> carQueue;

    public Road(final String name, final TrafficLight trafficLight) {
        this.name = name;
        this.trafficLight = trafficLight;
        this.carQueue = new ConcurrentLinkedQueue<>();

        trafficLight.addObserver(this);
    }

    public void addCarToQueue(final Car car) {
        carQueue.add(car);
        log.info("Сar {} got stuck in a traffic jam on {} road", car.getName(), this.getName());
    }

    @Override
    public void notifyObserver(final String color) {
        if (color == GREEN) {
            while (carQueue.size() > 0) {
                carQueue.poll().startDrivingIntention();
            }
        }
    }
}
