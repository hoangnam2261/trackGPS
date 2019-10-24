package com.nng.gps.mapper;

import io.jenetics.jpx.WayPoint;

public final class WayPointMapper {
    private WayPointMapper(){}

    public static com.nng.gps.domain.WayPoint toDomain(WayPoint wayPoint) {
        com.nng.gps.domain.WayPoint domain = new com.nng.gps.domain.WayPoint();
        domain.setLatitude(wayPoint.getLatitude().doubleValue())
              .setLongitude(wayPoint.getLongitude().doubleValue());
        wayPoint.getName().ifPresent(domain::setName);
        wayPoint.getSymbol().ifPresent(domain::setSymbol);
        return domain;
    }
}
