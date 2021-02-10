package org.example.trafficlight.rest.controller;

import lombok.RequiredArgsConstructor;
import org.example.trafficlight.rest.dto.TrafficStateDto;
import org.example.trafficlight.rest.service.TrafficService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("traffic")
@RequiredArgsConstructor
public class TrafficController {
    private final TrafficService trafficService;

    @GetMapping
    public TrafficStateDto getTraffic(
            @RequestParam(value = "from", required = false) Instant from,
            @RequestParam(value = "to", required = false) Instant to) {
        return trafficService.getAll(from, to);
    }
}
