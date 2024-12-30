package com.camel.portal_vt.dtos.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Leg(
        Distance distance,
        Duration duration,
        List<Step> steps
) {}
