package com.nng.gps.repository;

import com.nng.gps.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GPSRepositoryTest {

    @Autowired
    private IGPSRepository gpsRepository;

    @Test
    @Transactional
    public void shouldFindGPSById() {
        GPS stubGPS = getStubGPS();
        GPS save = gpsRepository.save(stubGPS);
        GPS found = gpsRepository.getById(save.getId());
        assertEquals(stubGPS.getUserId(), found.getUserId());
        assertEquals(stubGPS.getCreator(), found.getCreator());
        assertEquals(stubGPS.getVersion(), found.getVersion());
        Metadata stubMetadata = stubGPS.getMetadata();
        Metadata foundMetadata = found.getMetadata();
        assertEquals(stubMetadata.getName(), foundMetadata.getName());
        assertEquals(stubMetadata.getDescription(), foundMetadata.getDescription());
        assertEquals(stubMetadata.getTime(), foundMetadata.getTime());
        Link stubLink = stubMetadata.getLink();
        Link foundLink = foundMetadata.getLink();
        assertEquals(stubLink.getHref(), foundLink.getHref());
        assertEquals(stubLink.getText(), foundLink.getText());
        assertEquals(stubGPS.getWayPoints().size(), found.getWayPoints().size());
        assertEquals(stubGPS.getTracks().size(), found.getTracks().size());
        assertEquals(stubGPS.getTracks().get(0).getTrackSegments().size(),
                     found.getTracks().get(0).getTrackSegments().size());
        assertEquals(stubGPS.getTracks().get(0).getTrackSegments().get(0).getWayPoints().size(),
                     found.getTracks().get(0).getTrackSegments().get(0).getWayPoints().size());
    }


    private GPS getStubGPS() {
        return new GPS().setUserId("hoangnam2261")
                        .setCreator("OruxMaps v.7.1.6 Donate")
                        .setVersion("1.1")
                        .setWayPoints(getStubWayPoints())
                        .setMetadata(getStubMetadata())
                        .setTracks(getStubTracks());
    }

    private List<WayPoint> getStubWayPoints() {
        List<WayPoint> wayPoints = new ArrayList<>();
        wayPoints.add(
                new WayPoint().setLatitude(42.2205377)
                              .setLongitude(-1.4564538)
                              .setName("Sorteamos por arriba")
                              .setSymbol("/static/wpt/Waypoint"));
        wayPoints.add(
                new WayPoint().setLatitude(42.2208346)
                              .setLongitude(-1.4544232)
                              .setName("Sorteamos por arriba")
                              .setSymbol("/static/wpt/Waypoint")
        );
        return wayPoints;
    }

    private Metadata getStubMetadata() {
        return new Metadata().setName("Bardenas Reales: Piskerra y el Paso de los Ciervos")
                             .setDescription("Este espectacular Parque Natural semidesértico")
                             .setLink(getStubLink())
                             .setTime(Timestamp.from(Instant.parse("2017-10-22T09:41:33Z")));
    }

    private Link getStubLink() {
        return new Link()
                .setText("OruxMaps")
                .setHref("http://www.oruxmaps.com");
    }

    private List<Track> getStubTracks() {
        List<WayPoint> wayPoints = new ArrayList<>();
        wayPoints.add(
                new WayPoint().setLatitude(42.2208895)
                              .setLongitude(-1.4580696)
                              .setElevation(315.86)
                              .setTime(Timestamp.from(Instant.parse("2017-10-22T09:41:38Z"))));
        wayPoints.add(
                new WayPoint().setLatitude(42.2208228)
                              .setLongitude(-1.458099)
                              .setElevation(316.03888000000006)
                              .setTime(Timestamp.from(Instant.parse("2017-10-22T09:43:18Z"))));
        wayPoints.add(
                new WayPoint().setLatitude(42.2207764)
                              .setLongitude(-1.4581063)
                              .setElevation(316.1368)
                              .setTime(Timestamp.from(Instant.parse("2017-10-22T09:43:26Z"))));
        TrackSegment trackSegment = new TrackSegment().setWayPoints(wayPoints);
        List<TrackSegment> trackSegments = new ArrayList<>();
        trackSegments.add(trackSegment);
        Track track = new Track();
        track.setTrackSegments(trackSegments);
        List<Track> tracks = new ArrayList<>();
        tracks.add(track);
        return tracks;
    }
}
