package com.nng.gps.mapper;


import io.jenetics.jpx.Link;

public class LinkMapper {
    private LinkMapper() {}

    public static com.nng.gps.domain.Link toDomain(Link dto) {
        com.nng.gps.domain.Link domain = new com.nng.gps.domain.Link();
        domain.setHref(dto.getHref().toString());
        dto.getText()
            .ifPresent(domain::setText);
        return domain;
    }

    public static Link toDTO(com.nng.gps.domain.Link domain) {
        return Link.of(domain.getHref(), domain.getText(), null);
    }
}
