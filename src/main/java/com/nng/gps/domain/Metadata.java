package com.nng.gps.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Metadata {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(length = 6000)
    private String description;

    private String author;

    private Timestamp time;

    @OneToOne(cascade = CascadeType.ALL)
    private Link link;

    public Metadata setName(String name) {
        this.name = name;
        return this;
    }

    public Metadata setDescription(String description) {
        this.description = description;
        return this;
    }

    public Metadata setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Metadata setLink(Link link) {
        this.link = link;
        return this;
    }

    public Metadata setTime(Timestamp time) {
        this.time = time;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public Link getLink() {
        return link;
    }

    public Timestamp getTime() {
        return time;
    }
}
