package com.nng.gps.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Track {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @OneToMany
    private List<TrackSegment> trackSegments;
}
