package org.example.trafficlight.db.repository;

import org.example.trafficlight.db.entity.Traffic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface TrafficRepository extends JpaRepository<Traffic, Long> {
    List<Traffic> getAllByTimeBetween(Instant from, Instant to);
}
