package com.nng.gps.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class GPS {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    private String creator;

    private String version;

    private String xmlns;

    @OneToOne
    private Metadata metadata;

    @OneToMany
    private List<WayPoint> wayPoints;

    @OneToMany
    private List<Track> tracks;
}
