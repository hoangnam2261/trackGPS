package com.nng.gps.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Link {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    private String href;

    private String text;
}
