package com.nng.gps.service.impl;

import com.nng.gps.domain.GPS;
import com.nng.gps.dto.GeneralGPSDTO;
import com.nng.gps.exception.GPXFormatException;
import com.nng.gps.exception.NotExistedTrackException;
import com.nng.gps.mapper.GPSMapper;
import com.nng.gps.repository.IGPSRepository;
import com.nng.gps.service.IGPSService;
import io.jenetics.jpx.GPX;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(GPSServiceImpl.class);

    @Autowired
    IGPSRepository gpsRepository;

    @Override
    public void saveGPS(GPX gpx, String userId) {
        GPS entity = GPSMapper.toDomain(gpx);
        entity.setUserId(userId);
        gpsRepository.save(entity);
    }

    @Override
    public GPX parseGPX(MultipartFile multipartFile) throws GPXFormatException {
        GPX gpx;
        try(InputStream inputStream = multipartFile.getInputStream()) {
            gpx = GPX.read(inputStream);
        } catch (IOException e) {
            LOGGER.debug("Fail to parse gpx file", e);
            throw new GPXFormatException("File is not in gpx standard", e);
        }
        return gpx;
    }

    @Override
    public GPX getGPXById(Long id) throws NotExistedTrackException {
        GPS gps = gpsRepository.getById(id);
        if (gps == null) {
            throw new NotExistedTrackException("Track is non existence");
        }
        Hibernate.initialize(gps.getWayPoints());
        Hibernate.initialize(gps.getTracks());
        return GPSMapper.toDTO(gps);
    }

    @Override
    public List<GeneralGPSDTO> getLatest(Integer size, Integer offset) {
        return gpsRepository
                .findAll(new PageRequest(offset, size, new Sort("createdTime")))
                .getContent()
                .stream()
                .map(gps -> new GeneralGPSDTO()
                        .setTrackId(gps.getId())
                        .setTitle(gps.getMetadata().getName())
                        .setCreatedTime(gps.getCreatedTime())
                        .setUserId(gps.getUserId()))
                .collect(Collectors.toList());
    }
}
