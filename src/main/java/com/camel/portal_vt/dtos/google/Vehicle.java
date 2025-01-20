package com.camel.portal_vt.dtos.google;

import com.camel.portal_vt.enums.Transports;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vehicle(
        String name,
        String type,
        double value
) {
    public Vehicle {
        double tariff = switch (type.toUpperCase()) {
            case "BUS" -> 8.5;
            case "SUBWAY" -> 5.2;
            default -> 0.0;
        };
        
        value = tariff;
    }
}
