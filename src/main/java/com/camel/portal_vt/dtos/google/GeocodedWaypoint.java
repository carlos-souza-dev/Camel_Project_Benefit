package com.camel.portal_vt.dtos.google;

import java.util.List;

public record GeocodedWaypoint(
        String geocoder_status,
        String place_id,
        List<String> types
) {}