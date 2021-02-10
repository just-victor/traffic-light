package org.example.trafficlight.rest.dto;

import lombok.Data;
import org.example.trafficlight.db.entity.Traffic;

import java.util.List;
import java.util.Map;
import java.util.Queue;

@Data
public class TrafficStateDto {
    private Map<String, Queue<CarDto>> waitingCarsOnRoad;
    private List<Traffic> allTraffic;
}
