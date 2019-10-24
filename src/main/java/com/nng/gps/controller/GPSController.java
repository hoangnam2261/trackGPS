package com.nng.gps.controller;

import com.nng.gps.domain.GPS;
import com.nng.gps.mapper.GPSMapper;
import com.nng.gps.repository.GPSRepository;
import io.jenetics.jpx.GPX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class GPSController {

    @Autowired
    GPSRepository gpsRepository;

    @PostMapping("/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            GPX gpx = GPX.read(file.getInputStream());
            GPS entity = GPSMapper.toDomain(gpx);
            gpsRepository.save(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
