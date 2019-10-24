package com.nng.gps.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Link {

    @Id
    @GeneratedValue
    private Long id;

    private String href;

    private String text;

    public Link setHref(String href) {
        this.href = href;
        return this;
    }

    public Link setText(String text) {
        this.text = text;
        return this;
    }
}
