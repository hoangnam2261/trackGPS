package com.nng.gps.mapper;

import com.nng.gps.domain.GPS;
import com.nng.gps.domain.Track;
import com.nng.gps.domain.TrackSegment;
import io.jenetics.jpx.GPX;
import org.hibernate.Hibernate;

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

    public static GPX toDTO(GPS gps) {
        GPX.Builder builder = GPX.builder();
        builder.creator(gps.getCreator())
               .version(GPX.Version.of(gps.getVersion()));
        if (gps.getMetadata() != null) {
            builder.metadata(MetadataMapper.toDTO(gps.getMetadata()));
        }
        if (Hibernate.isInitialized(gps.getWayPoints())) {
            builder.wayPoints(gps.getWayPoints()
                    .stream()
                    .map(WayPointMapper::toDTO)
                    .collect(Collectors.toList()));
        }
        if (Hibernate.isInitialized(gps.getTracks())) {
            builder.tracks(
                    gps.getTracks()
                            .stream()
                            .map(track -> {
                                io.jenetics.jpx.Track.Builder trackBuilder = io.jenetics.jpx.Track.builder();
                                trackBuilder.segments(
                                        track.getTrackSegments()
                                                .stream()
                                                .map(trackSegment -> {
                                                    io.jenetics.jpx.TrackSegment.Builder trackSegmentBuilder = io.jenetics.jpx.TrackSegment.builder();
                                                    trackSegmentBuilder.points(
                                                            trackSegment.getWayPoints()
                                                                    .stream()
                                                                    .map(WayPointMapper::toDTO)
                                                                    .collect(Collectors.toList()));
                                                    return trackSegmentBuilder.build();
                                                })
                                                .collect(Collectors.toList()));
                                return trackBuilder.build();
                            })
                            .collect(Collectors.toList())
            );
        }
        return builder.build();
    }
}
