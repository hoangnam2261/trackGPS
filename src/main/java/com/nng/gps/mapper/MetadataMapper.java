package com.nng.gps.mapper;

import io.jenetics.jpx.Metadata;
import io.jenetics.jpx.Person;

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
        if(!metadata.getLinks().isEmpty()) {
            domain.setLink(LinkMapper.toDomain(metadata.getLinks().get(0)));
        }
        return domain;
    }
}
