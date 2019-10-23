package com.nng.gps.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class TrackSegment {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @OneToMany
    private List<WayPoint> wayPoints;
}
