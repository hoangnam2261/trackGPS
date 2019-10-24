package com.nng.gps.mapper;


import io.jenetics.jpx.Link;

public class LinkMapper {
    private LinkMapper() {}

    public static com.nng.gps.domain.Link toDomain(Link link) {
        com.nng.gps.domain.Link domain = new com.nng.gps.domain.Link();
        domain.setHref(link.getHref().toString());
        link.getText()
            .ifPresent(domain::setText);
        return domain;
    }
}
