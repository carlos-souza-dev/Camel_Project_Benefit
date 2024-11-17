package com.camel.portal_vt.dtos.google;

public record TransitDetails(
        Stop arrival_stop,
        Time arrival_time,
        Stop departure_stop,
        Time departure_time,
        String headsign,
        Line line,
        int num_stops
) {}
