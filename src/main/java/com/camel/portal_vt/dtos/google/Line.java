package com.camel.portal_vt.dtos.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Line(
        String color,
        String short_name,
        String text_color,
        Vehicle vehicle
) {}
