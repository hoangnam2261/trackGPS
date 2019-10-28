package com.nng.gps.controller;

import com.nng.gps.dto.GeneralGPSDTO;
import com.nng.gps.exception.GPXFormatException;
import com.nng.gps.exception.NotExistedTrackException;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class GPSController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GPSController.class);

    @Autowired
    private IGPSService gpsService;

    @Value("${gpx.file.max-KB-size}")
    private Long acceptedKBSize;


    @PostMapping("gpx/uploadFile")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file, Principal principal) throws GPXFormatException {
        if (!GPXFileValidator.validateGPXFile(file, acceptedKBSize)) {
            LOGGER.debug("Wrong format file. File size : {} byte. Content type: {}",
                    file.getSize(),
                    file.getContentType());
            throw new GPXFormatException("File is not in correct standard format.");
        }
        GPX gpx = gpsService.parseGPX(file);
        gpsService.saveGPS(gpx, principal.getName());
        HashMap<String, Object> response = new HashMap<>();
        response.put("result", "success");
        response.put("status", HttpStatus.CREATED.value());
        return response;
    }

    @GetMapping(value = "gpx/{gpxId}", produces = MediaType.APPLICATION_XML_VALUE)
    public byte[] getFileGPX(@NotNull @PathVariable Long gpxId) throws NotExistedTrackException, IOException {
        GPX gpx = gpsService.getGPXById(gpxId);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            GPX.write(gpx, outputStream);
        } catch (IOException e) {
            LOGGER.error("Write gpx to output stream fail", e);
            throw e;
        }
        return outputStream.toByteArray();
    }

    @GetMapping("gpx/latest")
    public List<GeneralGPSDTO> getLatest(@RequestParam @Max(12) Integer pageSize, @RequestParam Integer offset) {
        return gpsService.getLatest(pageSize, offset);
    }
}
