package org.example.trafficlight.rest.core.traffic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.trafficlight.ColorLightObserver;
import org.example.trafficlight.rest.core.Notifier;

import java.util.LinkedList;
import java.util.List;

import static org.example.trafficlight.rest.core.traffic.TrafficColor.GREEN;
import static org.example.trafficlight.rest.core.traffic.TrafficColor.RED;

@Slf4j
@Getter
public class TrafficLight implements Notifier {
    private final int index;
    private String color;
    private List<ColorLightObserver> observer;

    public TrafficLight(final int index, final String color) {
        this.index = index;
        this.color = color;
        observer = new LinkedList<>();
    }

    public String changeColor() {
            color = getColor() == GREEN
                    ? RED
                    : GREEN;

        log.info("TL{} Color changed to: {}", index, color);
        notifyObserver();
        return color;
    }

    public void addObserver(ColorLightObserver colorLightObserver) {
        this.observer.add(colorLightObserver);
    }

    @Override
    public void notifyObserver() {
        observer.forEach((obs) -> obs.notifyObserver(this.color));
    }
}
