package com.camel.portal_vt.dtos;

import com.camel.portal_vt.dtos.google.TransitDetails;

import java.math.BigDecimal;
import java.util.List;

public record SummaryRouteDTO(
        String direction,
        String distance,
        String duration,
        List<RouteDetailsDTO> routeDetails,
        BigDecimal totalValue
) {
    public SummaryRouteDTO(String direction,
                           String distance,
                           String duration,
                           List<RouteDetailsDTO> routeDetails,
                           BigDecimal totalValue) {
        this.direction = direction;
        this.distance = distance;
        this.duration = duration;
        this.routeDetails = routeDetails;
        this.totalValue = totalValue;
    }

    ;
}
