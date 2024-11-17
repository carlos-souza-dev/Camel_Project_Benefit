package com.camel.portal_vt.dtos.google;

import java.util.List;

public record Line(
        List<Agency> agencies,
        String color,
        String name,
        String short_name,
        String text_color,
        String url,
        Vehicle vehicle
) {}
