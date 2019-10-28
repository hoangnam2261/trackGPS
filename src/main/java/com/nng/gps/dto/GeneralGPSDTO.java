package com.nng.gps.dto;

import java.sql.Timestamp;

public class GeneralGPSDTO {

    private Long trackId;

    private String title;

    private Timestamp createdTime;

    private String userId;

    public String getTitle() {
        return title;
    }

    public GeneralGPSDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public GeneralGPSDTO setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public GeneralGPSDTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Long getTrackId() {
        return trackId;
    }

    public GeneralGPSDTO setTrackId(Long trackId) {
        this.trackId = trackId;
        return this;
    }
}
