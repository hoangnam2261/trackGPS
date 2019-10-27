package com.nng.gps.service.impl;

import com.nng.gps.domain.GPS;
import com.nng.gps.dto.GPSDTO;
import com.nng.gps.mapper.GPSMapper;
import com.nng.gps.repository.GPSRepository;
import com.nng.gps.service.IGPSService;
import io.jenetics.jpx.GPX;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GPSServiceImpl implements IGPSService {

    @Autowired
    GPSRepository gpsRepository;

    @Override
    public void saveGPS(MultipartFile multipartFile, String userId) throws IOException {
        GPX gpx;
        try(InputStream inputStream = multipartFile.getInputStream()) {
            gpx = GPX.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        GPS entity = GPSMapper.toDomain(gpx);
        entity.setUserId(userId);
        gpsRepository.save(entity);
    }

    @Override
    public GPX getGPXById(Long id) {
        GPS gps = gpsRepository.getById(id);
        Hibernate.initialize(gps.getWayPoints());
        Hibernate.initialize(gps.getTracks());
        return GPSMapper.toDTO(gps);
    }

    @Override
    public List<GPSDTO> getLatest(Integer size, Integer offset) {
        return gpsRepository
                .findAll(new PageRequest(offset, size, new Sort("createdTime")))
                .getContent()
                .stream()
                .map(gps -> new GPSDTO()
                        .setTrackId(gps.getId())
                        .setTitle(gps.getMetadata().getName())
                        .setCreatedTime(gps.getCreatedTime())
                        .setUserId(gps.getUserId()))
                .collect(Collectors.toList());
    }
}
