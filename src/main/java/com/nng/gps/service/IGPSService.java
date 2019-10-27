package com.nng.gps.service;

import com.nng.gps.dto.GPSDTO;
import com.nng.gps.exception.GPXFormatException;
import io.jenetics.jpx.GPX;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IGPSService {
    void saveGPS(GPX gpx, String userId);

    GPX parseGPX(MultipartFile multipartFile) throws GPXFormatException;

    GPX getGPXById(Long id);

    List<GPSDTO> getLatest(Integer size, Integer offset);
}
