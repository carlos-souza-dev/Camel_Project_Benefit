package com.camel.portal_vt.dtos.google;

import java.util.List;

public record RouteInformation(
        List<GeocodedWaypoint> geocoded_waypoints,
        List<Route> routes,
        String status
) {}
