package com.camel.portal_vt.dtos.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true)
public record Step(
        Distance distance,
        Duration duration,
        Location end_location,
        String html_instructions,
        Polyline polyline,
        Location start_location,
        TransitDetails transit_details,
        String travel_mode,
        List<Step> steps,
        String maneuver
) {}
