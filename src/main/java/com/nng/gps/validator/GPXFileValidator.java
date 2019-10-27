package com.nng.gps.validator;

import org.springframework.web.multipart.MultipartFile;

public final class GPXFileValidator {

    public static final String GPX_CONTENT_TYPE = "application/gpx+xml";

    private GPXFileValidator(){};

    public static boolean validateGPXFile(MultipartFile file, long acceptedKBSize) {
        return !file.isEmpty()
                && file.getSize() / 1024 <= acceptedKBSize
                && GPX_CONTENT_TYPE.equals(file.getContentType());
    }
}
