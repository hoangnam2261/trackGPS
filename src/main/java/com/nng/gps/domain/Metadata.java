package com.nng.gps.domain;

import javax.persistence.*;

@Entity
public class Metadata {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    private String name;

    private String description;

    private String author;

    @OneToOne
    private Link link;
}
