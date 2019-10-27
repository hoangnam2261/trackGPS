package com.nng.gps.service.impl;

import com.nng.gps.exception.GPXFormatException;
import com.nng.gps.service.IGPSService;
import com.nng.gps.validator.GPXFileValidator;
import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Metadata;
import io.jenetics.jpx.WayPoint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.ZonedDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GPSServiceImplTest {

    public static final String DEFAULT_SYMBOL = "/static/wpt/Waypoint";
    @Autowired
    private IGPSService gpsService;

    @Test(expected = GPXFormatException.class)
    public void testParseBadFormatGPX() throws IOException, GPXFormatException {
        File badFile = new File("src/test/java/com/nng/gps/sample_test_file/bad_gpx.gpx");
        try(FileInputStream inputStream = new FileInputStream(badFile);) {
            MockMultipartFile mockMultipartFile =
                    new MockMultipartFile(
                            "file",
                            badFile.getName(),
                            GPXFileValidator.GPX_CONTENT_TYPE,
                            inputStream);
            gpsService.parseGPX(mockMultipartFile);
        }
    }

    @Test
    public void testParseCorrectGPX() throws IOException, GPXFormatException {
        File badFile = new File("src/test/java/com/nng/gps/sample_test_file/simple.gpx");
        try(FileInputStream inputStream = new FileInputStream(badFile);) {
            MockMultipartFile mockMultipartFile =
                    new MockMultipartFile(
                            "file",
                            badFile.getName(),
                            GPXFileValidator.GPX_CONTENT_TYPE,
                            inputStream);
            GPX gpx = gpsService.parseGPX(mockMultipartFile);
            Assert.assertEquals("1.1", gpx.getVersion());
            Assert.assertEquals("OruxMaps v.7.1.6 Donate", gpx.getCreator());
            Assert.assertTrue(gpx.getMetadata().isPresent());
            Metadata metaData = gpx.getMetadata().get();
            Assert.assertEquals(
                    "Bardenas Reales: Piskerra y el Paso de los Ciervos",
                    metaData.getName().get());
            Assert.assertEquals(
                    "Este espectacular Parque Natural semidesértico",
                    metaData.getDescription().get());
            Assert.assertTrue(metaData.getAuthor().isPresent());
            Assert.assertEquals("http://www.oruxmaps.com", metaData.getLinks().get(0).getHref().toString());
            Assert.assertEquals("OruxMaps", metaData.getLinks().get(0).getText().get());
            Assert.assertEquals(ZonedDateTime.parse("2017-10-22T09:41:33Z"), metaData.getTime().get());
            Assert.assertArrayEquals(
                    gpx.getWayPoints().toArray(),
                    new WayPoint[]{
                            WayPoint.builder()
                                    .lat(42.2205377)
                                    .lon(-1.4564538)
                                    .name("Sorteamos por arriba")
                                    .sym(DEFAULT_SYMBOL)
                                    .build(),
                            WayPoint.builder()
                                    .lat(42.2208346)
                                    .lon(-1.4544232)
                                    .name("Senda")
                                    .sym(DEFAULT_SYMBOL)
                                    .build()});
            Assert.assertArrayEquals(
                    new WayPoint[]{
                            WayPoint.builder()
                                    .lat(42.2208895)
                                    .lon(-1.4580696)
                                    .ele(315.86)
                                    .time(ZonedDateTime.parse("2017-10-22T09:41:38Z"))
                                    .build(),
                            WayPoint.builder()
                                    .lat(42.2208228)
                                    .lon(-1.458099)
                                    .ele(316.03888000000006)
                                    .time(ZonedDateTime.parse("2017-10-22T09:43:18Z"))
                                    .build(),
                            WayPoint.builder()
                                    .lat(42.2207764)
                                    .lon(-1.4581063)
                                    .ele(316.1368)
                                    .time(ZonedDateTime.parse("2017-10-22T09:43:26Z"))
                                    .build()},
                    gpx.getTracks().get(0).getSegments().get(0).getPoints().toArray());
        }
    }
}