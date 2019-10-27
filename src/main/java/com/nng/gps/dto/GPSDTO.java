package com.nng.gps.dto;

import java.sql.Timestamp;

public class GPSDTO {

    private Long trackId;

    private String title;

    private Timestamp createdTime;

    private String userId;

    public String getTitle() {
        return title;
    }

    public GPSDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public GPSDTO setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public GPSDTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Long getTrackId() {
        return trackId;
    }

    public GPSDTO setTrackId(Long trackId) {
        this.trackId = trackId;
        return this;
    }
}
