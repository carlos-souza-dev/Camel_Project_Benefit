package com.camel.portal_vt.dtos.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TransitDetails(
        Stop arrival_stop,
        Stop departure_stop,
        String headsign,
        Line line,
        int num_stops
) {}
