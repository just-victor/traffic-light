package org.example.trafficlight.rest.service;

import lombok.RequiredArgsConstructor;
import org.example.trafficlight.db.entity.Traffic;
import org.example.trafficlight.db.repository.TrafficRepository;
import org.example.trafficlight.rest.core.Car;
import org.example.trafficlight.rest.core.Road;
import org.example.trafficlight.rest.dto.CarDto;
import org.example.trafficlight.rest.dto.TrafficStateDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toMap;


@Service
@RequiredArgsConstructor
public class TrafficService {
    private final TrafficRepository repository;
    private final List<Road> roads;

    public TrafficStateDto getAll(final Instant from, final Instant to) {
        List<Traffic> cars;

        if (isNull(from) || isNull(to)) {
            cars = repository.findAll();
        } else {
            cars = repository.getAllByTimeBetween(from, to);
        }
        TrafficStateDto trafficStateDto = new TrafficStateDto();
        Map<String, Queue<CarDto>> waitingCars = roads.stream()
                .collect(toMap(Road::getName, road -> road.getCarQueue().stream()
                        .map(Car::getName)
                        .map(CarDto::new)
                        .collect(Collectors.toCollection(LinkedList::new)))
                );
        trafficStateDto.setWaitingCarsOnRoad(waitingCars);
        trafficStateDto.setAllTraffic(cars);
        return trafficStateDto;
    }

    public void save(Traffic traffic) {
        repository.save(traffic);
    }
}
