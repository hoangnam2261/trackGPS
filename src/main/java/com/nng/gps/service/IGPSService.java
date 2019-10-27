package com.nng.gps.service;

import com.nng.gps.dto.GPSDTO;
import io.jenetics.jpx.GPX;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IGPSService {
    public void saveGPS(MultipartFile multipartFile, String userId) throws IOException;
    public GPX getGPXById(Long id);
    List<GPSDTO> getLatest(Integer size, Integer offset);
}
