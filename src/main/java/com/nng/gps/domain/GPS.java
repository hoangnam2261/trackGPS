package com.nng.gps.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GPS {
    @Id
    @GeneratedValue
    private Long id;

    private String userId;

    private String creator;

    private String version;

    @OneToOne(cascade = CascadeType.ALL)
    private Metadata metadata;

    @OneToMany(mappedBy = "gps", cascade = CascadeType.ALL)
    private List<WayPoint> wayPoints = new ArrayList<>();

    @OneToMany(mappedBy = "gps", cascade = CascadeType.ALL)
    private List<Track> tracks;

    @CreationTimestamp
    private Timestamp createdTime;

    public GPS setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public GPS setCreator(String creator) {
        this.creator = creator;
        return this;
    }

    public GPS setVersion(String version) {
        this.version = version;
        return this;
    }

    public GPS setMetadata(Metadata metadata) {
        this.metadata = metadata;
        return this;
    }

    public GPS setWayPoints(List<WayPoint> wayPoints) {
        this.wayPoints = wayPoints;
        wayPoints.forEach(wayPoint -> wayPoint.setGps(this));
        return this;
    }

    public GPS setTracks(List<Track> tracks) {
        this.tracks = tracks;
        tracks.forEach(track -> track.setGps(this));
        return this;
    }

    public GPS setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    public String getCreator() {
        return creator;
    }

    public String getVersion() {
        return version;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public List<WayPoint> getWayPoints() {
        return wayPoints;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
