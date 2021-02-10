package org.example.trafficlight.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Table
@Entity
@NoArgsConstructor
@Accessors(chain = true)
public class Traffic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Instant time;

    @Column
    private String roadName;

    @Column
    private String carName;

    @Column
    private String color;
}
