package com.nng.gps.exception;

public class GPXFormatException extends Exception {

    public GPXFormatException(String msg) {
        super(msg);
    }

    public GPXFormatException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
