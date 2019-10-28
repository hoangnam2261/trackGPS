package com.nng.gps.service;

import com.nng.gps.dto.GeneralGPSDTO;
import com.nng.gps.exception.GPXFormatException;
import com.nng.gps.exception.NotExistedTrackException;
import io.jenetics.jpx.GPX;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IGPSService {
    void saveGPS(GPX gpx, String userId);

    GPX parseGPX(MultipartFile multipartFile) throws GPXFormatException;

    GPX getGPXById(Long id) throws NotExistedTrackException;

    List<GeneralGPSDTO> getLatest(Integer size, Integer offset);
}
