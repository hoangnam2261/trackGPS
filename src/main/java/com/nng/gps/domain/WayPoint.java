package com.nng.gps.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class WayPoint {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull
    private String latitude;

    @NotNull
    private String longitude;

    private String elevation;

    private String name;

    private String symbol;

    private Timestamp timestamp;
}
