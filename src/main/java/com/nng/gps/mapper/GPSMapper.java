package com.nng.gps.mapper;

import com.nng.gps.domain.GPS;
import com.nng.gps.domain.Track;
import com.nng.gps.domain.TrackSegment;
import io.jenetics.jpx.GPX;

import java.util.stream.Collectors;

public final class GPSMapper {
    private GPSMapper(){}

    public static GPS toDomain(GPX gpx) {
        GPS gps = new GPS();
        gps.setCreator(gpx.getCreator())
           .setVersion(gpx.getVersion());
        gpx.getMetadata()
           .ifPresent(metadata -> gps.setMetadata(MetadataMapper.toDomain(metadata)));
        gps.setWayPoints(gpx.wayPoints()
           .map(WayPointMapper::toDomain)
           .collect(Collectors.toList()));
        gps.setTracks(
                gpx.getTracks()
                   .stream()
                   .map(track ->
                                new Track()
                                        .setTrackSegments(
                                                track.getSegments()
                                                     .stream()
                                                     .map(trackSegment ->
                                                                  new TrackSegment()
                                                                          .setWayPoints(
                                                                                  trackSegment.points()
                                                                                              .map(WayPointMapper::toDomain)
                                                                                              .collect(Collectors.toList())))
                                                     .collect(Collectors.toList())))
                   .collect(Collectors.toList()));
        return gps;
    }
}
