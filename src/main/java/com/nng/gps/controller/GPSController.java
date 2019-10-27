package com.nng.gps.controller;

import com.nng.gps.dto.GPSDTO;
import com.nng.gps.exception.GPXFormatException;
import com.nng.gps.service.IGPSService;
import com.nng.gps.validator.GPXFileValidator;
import io.jenetics.jpx.GPX;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
public class GPSController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GPSController.class);

    @Autowired
    private IGPSService gpsService;

    @Value("${gpx.file.max-KB-size}")
    private Long acceptedKBSize;


    @PostMapping("gpx/uploadFile")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadFile(@RequestParam("file") MultipartFile file, Principal principal) throws IOException, GPXFormatException {
        if (!GPXFileValidator.validateGPXFile(file, acceptedKBSize)) {
            LOGGER.debug("Wrong format file. File size : {} byte. Content type: {}",
                    file.getSize(),
                    file.getContentType());
            throw new GPXFormatException("File is not in correct standard format.");
        }
        gpsService.saveGPS(file, principal.getName());
    }

    @GetMapping(value = "gpx/{gpxId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] getFileGPX(@NotBlank @PathVariable Long gpxId) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            GPX.write(gpsService.getGPXById(gpxId), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    @GetMapping("gpx/latest")
    public List<GPSDTO> getLatest(@RequestParam @Max(12) Integer pageSize, @RequestParam Integer offset) {
        return gpsService.getLatest(pageSize, offset);
    }
}
