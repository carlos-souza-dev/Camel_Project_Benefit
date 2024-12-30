package com.camel.portal_vt.dtos.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Stop(
        String name
) {}
