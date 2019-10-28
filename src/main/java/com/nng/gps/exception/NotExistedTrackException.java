package com.nng.gps.exception;

public class NotExistedTrackException extends Exception{
    public NotExistedTrackException(String msg) {
        super(msg);
    }

    public NotExistedTrackException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
