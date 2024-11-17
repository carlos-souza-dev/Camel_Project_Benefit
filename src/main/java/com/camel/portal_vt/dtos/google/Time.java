package com.camel.portal_vt.dtos.google;

public record Time(
        String text,
        String time_zone,
        long value
) {}