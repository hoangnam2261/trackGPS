package com.nng.gps.controller;

import com.nng.gps.domain.GPS;
import com.nng.gps.mapper.GPSMapper;
import com.nng.gps.repository.GPSRepository;
import io.jenetics.jpx.GPX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class GPSController {

    @Autowired
    GPSRepository gpsRepository;

    @PostMapping("gpx/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            GPX gpx = GPX.read(file.getInputStream());
            GPS entity = GPSMapper.toDomain(gpx);
            gpsRepository.save(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("gpx/{gpxId}")
    public GPX getGPX(@PathVariable Long gpxId) {
        GPS byId = gpsRepository.getById(gpxId);
        return GPSMapper.toDTO(byId);
    }
}
