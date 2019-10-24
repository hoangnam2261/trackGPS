package com.nng.gps.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Track {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "track", cascade = CascadeType.ALL)
    private List<TrackSegment> trackSegments = new ArrayList<>();

    @ManyToOne
    private GPS gps;

    public Track setGps(GPS gps) {
        this.gps = gps;
        return this;
    }

    public Track setTrackSegments(List<TrackSegment> trackSegments) {
        this.trackSegments = trackSegments;
        trackSegments.forEach(trackSegment -> trackSegment.setTrack(this));
        return this;
    }
}
