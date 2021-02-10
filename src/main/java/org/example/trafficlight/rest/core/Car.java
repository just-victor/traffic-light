package org.example.trafficlight.rest.core;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.trafficlight.rest.core.traffic.TrafficLight;
import org.example.trafficlight.rest.core.traffic.TrafficObserver;

import java.util.concurrent.ThreadLocalRandom;

import static org.example.trafficlight.rest.core.traffic.TrafficColor.GREEN;

@Slf4j
@Data
@RequiredArgsConstructor
public class Car implements Notifier, Runnable {
    private final String name;
    private final TrafficObserver observer;
    private final TrafficLight trafficLight;
    private final boolean isAsync;
    private Road road;

    @Override
    public void notifyObserver() {
        log.info("Car {} passed", name);
        observer.notifyObserver(road, name);
    }

    @Override
    public void run() {
        waitRandomTime();
        if (trafficLight.getColor() == GREEN) {
            log.info("Car {} ran", name);
            this.notifyObserver();
        }
    }

    public void arrive(Road road) {
        this.road = road;
        log.info("Car \"{}\" arrived on \"{}\" road", this.name, road.getName());
    }

    private void waitRandomTime() {
        var intentDelay = ThreadLocalRandom.current().nextInt(4999);
        try {
            Thread.sleep(intentDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startDrivingIntention() {
        if (isAsync) {
            startAsyncIntent();
        } else {
            startSyncIntent();
        }
    }

    private void startSyncIntent() {
        synchronized (road.getTrafficLight()) {
            if (road.getTrafficLight().getColor() == GREEN) {
                notifyObserver();
                return;
            }
        }

        road.addCarToQueue(this);
    }

    private void startAsyncIntent() {
        if (road.getTrafficLight().getColor() == GREEN) {
            notifyObserver();
            return;
        }

        road.addCarToQueue(this);
    }
}
