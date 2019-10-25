package com.nng.gps.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class TrackSegment {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "trackSegment", cascade = CascadeType.ALL)
    private List<WayPoint> wayPoints;

    @ManyToOne
    private Track track;

    public TrackSegment setTrack(Track track) {
        this.track = track;
        return this;
    }

    public TrackSegment setWayPoints(List<WayPoint> wayPoints) {
        this.wayPoints = wayPoints;
        wayPoints.forEach(wayPoint -> wayPoint.setTrackSegment(this));
        return this;
    }

    public List<WayPoint> getWayPoints() {
        return wayPoints;
    }
}
