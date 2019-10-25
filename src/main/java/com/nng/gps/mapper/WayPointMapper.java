package com.nng.gps.mapper;

import io.jenetics.jpx.WayPoint;

import java.sql.Timestamp;

public final class WayPointMapper {
    private WayPointMapper(){}

    public static com.nng.gps.domain.WayPoint toDomain(WayPoint wayPoint) {
        com.nng.gps.domain.WayPoint domain = new com.nng.gps.domain.WayPoint();
        domain.setLatitude(wayPoint.getLatitude().doubleValue())
              .setLongitude(wayPoint.getLongitude().doubleValue());
        wayPoint.getName().ifPresent(domain::setName);
        wayPoint.getSymbol().ifPresent(domain::setSymbol);
        wayPoint.getElevation().ifPresent(length -> domain.setElevation(length.doubleValue()));
        wayPoint.getTime().ifPresent(zonedDateTime -> domain.setTime(Timestamp.from(zonedDateTime.toInstant())));
        return domain;
    }

    public static WayPoint toDTO(com.nng.gps.domain.WayPoint wayPoint) {
        WayPoint.Builder builder = WayPoint.builder();
        builder.lon(wayPoint.getLongitude())
               .lat(wayPoint.getLatitude());
        if (wayPoint.getName() != null) {
            builder.name(wayPoint.getName());
        }
        if (wayPoint.getSymbol() != null) {
            builder.sym(wayPoint.getSymbol());
        }
        if (wayPoint.getElevation() !=null) {
            builder.ele(wayPoint.getElevation());
        }
        if (wayPoint.getTime() != null) {
            builder.time(wayPoint.getTime().toInstant());
        }
        return builder.build();
    }
}
