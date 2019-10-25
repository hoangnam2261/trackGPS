package com.nng.gps.mapper;

import io.jenetics.jpx.Metadata;
import io.jenetics.jpx.Person;

import java.sql.Timestamp;

public final class MetadataMapper {
    private MetadataMapper() {};

    public static com.nng.gps.domain.Metadata toDomain(Metadata metadata) {
        com.nng.gps.domain.Metadata domain = new com.nng.gps.domain.Metadata();
        metadata.getAuthor()
                .flatMap(Person::getName)
                .ifPresent(domain::setAuthor);
        metadata.getDescription()
                .ifPresent(domain::setDescription);
        metadata.getName()
                .ifPresent(domain::setName);
        metadata.getTime()
                .ifPresent(zonedDateTime -> domain.setTime(Timestamp.from(zonedDateTime.toInstant())));
        if(!metadata.getLinks().isEmpty()) {
            domain.setLink(LinkMapper.toDomain(metadata.getLinks().get(0)));
        }
        return domain;
    }

    public static Metadata toDTO(com.nng.gps.domain.Metadata domain) {
        Metadata.Builder builder = Metadata.builder();
        if (domain.getAuthor() != null) {
            builder.author(domain.getAuthor());
        }
        if (domain.getDescription() != null) {
            builder.desc(domain.getDescription());
        }
        if (domain.getName() != null) {
            builder.name(domain.getName());
        }
        if (domain.getLink() != null) {
            builder.addLink(LinkMapper.toDTO(domain.getLink()));
        }
        if (domain.getTime() != null) {
            builder.time(domain.getTime().toInstant());
        }

        return builder.build();
    }
}
