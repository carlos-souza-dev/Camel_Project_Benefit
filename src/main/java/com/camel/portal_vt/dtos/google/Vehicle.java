package com.camel.portal_vt.dtos.google;

import com.camel.portal_vt.enums.Transports;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vehicle(
        String name,
        String type,
        BigDecimal value
) {
    public Vehicle {

        value = switch (type.toUpperCase()) {
            case "BUS" -> BigDecimal.valueOf(5.0);
            case "SUBWAY" -> BigDecimal.valueOf(5.2);
            case "TROLLEYBUS" -> BigDecimal.valueOf(6.05);
            case "TRAIN" -> BigDecimal.valueOf(5.2);
            case "TRAN" -> BigDecimal.valueOf(5.2);
            default -> BigDecimal.valueOf(0.0);
        };
    }
}
