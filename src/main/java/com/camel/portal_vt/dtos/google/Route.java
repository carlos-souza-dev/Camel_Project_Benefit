package com.camel.portal_vt.dtos.google;

import java.util.List;

public record Route(
        Bounds bounds,
        String copyrights,
        List<Leg> legs,
        Polyline overview_polyline,
        String summary,
        List<String> warnings,
        List<Integer> waypoint_order
) {}