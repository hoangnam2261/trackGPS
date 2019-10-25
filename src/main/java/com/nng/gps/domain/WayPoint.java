package com.nng.gps.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class WayPoint {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    private Double elevation;

    private String name;

    private String symbol;

    private Timestamp time;

    @ManyToOne
    private GPS gps;

    @ManyToOne
    private TrackSegment trackSegment;

    public WayPoint setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public WayPoint setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public WayPoint setName(String name) {
        this.name = name;
        return this;
    }

    public WayPoint setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public WayPoint setElevation(double elevation) {
        this.elevation = elevation;
        return this;
    }

    public WayPoint setTime(Timestamp time) {
        this.time = time;
        return this;
    }

    public WayPoint setGps(GPS gps) {
        this.gps = gps;
        return this;
    }

    public WayPoint setTrackSegment(TrackSegment trackSegment) {
        this.trackSegment = trackSegment;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Double getElevation() {
        return elevation;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public Timestamp getTime() {
        return time;
    }
}
