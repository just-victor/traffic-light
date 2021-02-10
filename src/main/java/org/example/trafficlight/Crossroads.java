package org.example.trafficlight;

import lombok.RequiredArgsConstructor;
import org.example.trafficlight.rest.core.Car;
import org.example.trafficlight.rest.core.Road;
import org.example.trafficlight.rest.core.traffic.TrafficObserver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class Crossroads implements Runnable {
    private final TrafficObserver trafficObserver;
    private final List<Road> roads;
    @Value("${app.number-traffic-generation-threads}")
    private int threadCount;
    @Value("${app.is-async}")
    private boolean isAsync;

    public void startTraffic() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.scheduleAtFixedRate(this::run, i, 5, TimeUnit.SECONDS);
        }
    }

    private Road getRandomRoad() {
        int roadIndex = ThreadLocalRandom.current().nextInt(roads.size());

        return roads.get(roadIndex);
    }

    private String generateCarName() {
        return "Car #" + Thread.currentThread().getName();
    }

    @Override
    public void run() {
        Road randomRoad = getRandomRoad();
        Car car = new Car(generateCarName(), trafficObserver, randomRoad.getTrafficLight(), isAsync);
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
            car.arrive(randomRoad);
            car.startDrivingIntention();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
