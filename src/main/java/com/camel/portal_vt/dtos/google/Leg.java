package com.camel.portal_vt.dtos.google;

import java.util.List;

public record Leg(
        Time arrival_time,
        Time departure_time,
        Distance distance,
        Duration duration,
        String end_address,
        Location end_location,
        String start_address,
        Location start_location,
        List<Step> steps,
        List<Object> traffic_speed_entry,
        List<Object> via_waypoint
) {}
